package com.cil.grpc;

import com.cil.Global.GlobalConfig;
import com.cil.Global.GlobalConstant;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by bob on 2017.08.18.
 */
public class SendServer {

    private static final Logger logger = LoggerFactory.getLogger(SendServer.class.getName());

    private Server server;

    public void start() throws IOException {
    /* The port on which the server should run */
        int port = Integer.valueOf(GlobalConfig.getValue("toKafkaPort"));
        server = ServerBuilder.forPort(port)
                .addService(new SenderImpl())
                .build()
                .start();
        logger.info("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                SendServer.this.stop();
                System.err.println("*** server shut down");
            }
        });
    }

    public void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    /**
     * Main launches the server from the command line.
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        final SendServer server = new SendServer();
        server.start();
        server.blockUntilShutdown();
    }

    static class SenderImpl extends SendServiceGrpc.SendServiceImplBase {

        @Override
        public void send(SendRequest req, StreamObserver<SendResponse> responseObserver) {
            SendResponse reply = SendResponse.newBuilder().setMessage(GlobalConstant.DESK_RESPONSE_SUCCESS).build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();

            // 处理消息
            ProcessRequest processRequest = new ProcessRequest();
            processRequest.requestFilterAndProcess(req);


        }
    }
}
