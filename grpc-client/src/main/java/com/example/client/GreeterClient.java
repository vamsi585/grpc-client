package com.example.client;

import com.example.proto.GreeterGrpc;
import com.example.proto.HelloReply;
import com.example.proto.HelloRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GreeterClient {

    public static void main(String[] args) {
        // 1. Create channel to connect to the server
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50051)
                .usePlaintext() // for non-SSL
                .build();

        // 2. Create a stub
        GreeterGrpc.GreeterBlockingStub stub = GreeterGrpc.newBlockingStub(channel);

        // 3. Prepare request
        HelloRequest request = HelloRequest.newBuilder()
                .setName("Alice")
                .build();

        // 4. Call the service
        HelloReply response = stub.sayHello(request);

        // 5. Print the response
        System.out.println("Server response: " + response.getMessage());

        // 6. Shutdown
        channel.shutdown();
    }
}
