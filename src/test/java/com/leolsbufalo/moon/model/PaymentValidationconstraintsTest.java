package com.leolsbufalo.moon.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class PaymentValidationconstraintsTest {

    private static Validator validator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void createPaymentWithWrongCurrency() {

        PaymentModel paymentModel = createPaymentModel();

        paymentModel.setCurrency("BR");

        Set<ConstraintViolation<PaymentModel>> violations = validator.validate(paymentModel);

        assertTrue(violations.size() > 1);
    }

    @Test
    public void createPaymentWithWrongValueInCents() {

        PaymentModel paymentModel = createPaymentModel();

        paymentModel.setValueInCents(-1234);

        Set<ConstraintViolation<PaymentModel>> violations = validator.validate(paymentModel);

        assertTrue(violations.size() > 1);
    }

    @Test
    public void createPaymentWithWrongInstallments() {

        PaymentModel paymentModel = createPaymentModel();

        paymentModel.setInstallments(-1);

        Set<ConstraintViolation<PaymentModel>> violations = validator.validate(paymentModel);

        assertTrue(violations.size() > 1);
    }


    public static PaymentModel createPaymentModel() {
        return new PaymentModel(
                "BRL",
                1050,
                1,
                List.of( new PaymentItemModel("Shield of the gods",
                        1,
                        1050)),
                new PaymentCardModel(
                        "credit card",
                        "00009123454345",
                        001,
                        "Septima Zenobia",
                        "09890809800",
                        04,
                        2060),
                new CostumerModel(
                        "Septima Zenobia",
                        "septimazenobia@email.com",
                        00,
                        01,
                        900009900,
                        "03/03/01",
                        "09890809800",
                        new CostumerAndressModel(
                                "Stret Septima Zenobia",
                                "90",
                                "Paralel universe",
                                "SP",
                                "Athenas",
                                "greek",
                                0000000
                        )
                )
        );
    }
}
