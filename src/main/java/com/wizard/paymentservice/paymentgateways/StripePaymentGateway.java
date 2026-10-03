package com.wizard.paymentservice.paymentgateways;

import com.stripe.Stripe;
import com.stripe.StripeClient;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class StripePaymentGateway implements  IPaymentGateway{

    @Value("${stripe.apiKey}")
    private String apiKey;

    @Override
    public String getPaymentLink(Long amount, String phoneNumber, String name, String email, String orderId) {
        try {

            Stripe.apiKey=this.apiKey;

            Price price= getPrice(amount);
            PaymentLinkCreateParams params =
                    PaymentLinkCreateParams.builder()
                            .addLineItem(
                                    PaymentLinkCreateParams.LineItem.builder()
                                            .setPrice(price.getId())
                                            .setQuantity(1L)
                                            .build()
                            )
                            .build();

            PaymentLink paymentLink = PaymentLink.create(params);
            return paymentLink.getUrl();
        } catch (StripeException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }
        private Price getPrice(Long amount) {
            try {
        //StripeClient client = new StripeClient("sk_test_tR3PYbcVNZZ796tH88S4VQ2u");

                PriceCreateParams params =
                        PriceCreateParams.builder()
                                .setCurrency("usd")
                                .setUnitAmount(amount)
                                .setRecurring(
                                        PriceCreateParams.Recurring.builder()
                                                .setInterval(PriceCreateParams.Recurring.Interval.MONTH)
                                                .build()
                                )
                                .setProductData(
                                        PriceCreateParams.ProductData.builder().setName("Gold Plan").build()
                                )
                                .build();

//                Price price = client.v1().prices().create(params);
                Price price = Price.create(params);
                return price;
            } catch (StripeException exception) {
                throw new RuntimeException(exception.getMessage());
            }
        }
    }
