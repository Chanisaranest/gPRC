package SumClient;

import com.proto.greet.GreetRequest;
import com.proto.greet.GreetResponse;
import com.proto.greet.GreetServiceGrpc;
import com.proto.greet.Greeting;
import com.proto.sum.Sum;
import com.proto.sum.SumRequest;
import com.proto.sum.SumResponse;
import com.proto.sum.SumServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class SumClient {
    public static void main(String[] args){
        System.out.println("Hello gRPC Client");
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 55554)
                .usePlaintext()
                .build();
        System.out.println("Creating stub");
//        DummyServiceGrpc.DummyServiceBlockingStub syncClient
//                = DummyServiceGrpc.newBlockingStub(channel);

        SumServiceGrpc.SumServiceBlockingStub sumClient;
        sumClient = SumServiceGrpc.newBlockingStub(channel);
// created a protocol buffer greeting message
        Sum sum = Sum.newBuilder()
                .setNum1(5)
                .setNum2(10)
                .build();
//        Greeting greeting = Greeting.newBuilder()
//                .setFirstName("John")
//                .setLastName("Baan")
//                .build();
// created a protocol buffer greetRequest message
        SumRequest  sumRequest = SumRequest.newBuilder()
                .setSum(sum)
                .build();
//        GreetRequest greetRequest = GreetRequest.newBuilder()
//                .setGreeting(greeting)
//                .build();
// call the RPC and get back a GreetResponse (Protocol Buffers)
        SumResponse sumResponse = sumClient.sum(sumRequest);
//        GreetResponse greetResponse = greetClient.greet(greetRequest);
// show the result in GreetResponse message
        System.out.println(sumResponse.getResult());
        System.out.println("Shutting down channel");
        channel.shutdown();
//        System.out.println(greetResponse.getResult());
//        System.out.println("Shutting down channel");
//        channel.shutdown();
    }
}
