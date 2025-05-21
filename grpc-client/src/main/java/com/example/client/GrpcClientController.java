package com.example.client;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.proto.GreeterGrpc;
import com.example.proto.HelloReply;
import com.example.proto.HelloRequest;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@RestController
@RequestMapping("/grpc")
public class GrpcClientController {

    @GetMapping("/greet/{name}")
    public String greet(@PathVariable String name) {
       // String name = "vijay";

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        GreeterGrpc.GreeterBlockingStub stub = GreeterGrpc.newBlockingStub(channel);
        HelloRequest request = HelloRequest.newBuilder().setName(name).build();
        HelloReply reply = stub.sayHello(request);

        channel.shutdown();
        return reply.getMessage();
    }
}
