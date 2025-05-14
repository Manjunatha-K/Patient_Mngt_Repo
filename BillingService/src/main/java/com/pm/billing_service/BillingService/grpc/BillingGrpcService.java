package com.pm.billing_service.BillingService.grpc;

import billing.BillingResponse;
import billing.BillingServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class BillingGrpcService extends BillingServiceGrpc.BillingServiceImplBase {
    private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class);

    @Override
    public void createBillingAccount(billing.BillingRequest billingRequest,
                                     StreamObserver<BillingResponse> responseObserver){
        log.info("CreateBillingAccount request received {}", billingRequest.toString());

        //Business logic - eg. save to Database, Perform calculates

        BillingResponse response = BillingResponse.newBuilder().setAccountId("12345").setStatus("ACTIVE").build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
