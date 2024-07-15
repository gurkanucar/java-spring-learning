package org.gucardev.mixed.relationship_cases.feign_transaction;

import com.gucardev.springlearning.feign_transaction.client.ExampleClient;
import com.gucardev.springlearning.feign_transaction.client.MyFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MyService {

    private final MyEntityRepository myEntityRepository;

    private final MyFeignClient myFeignClient;
    private final ExampleClient exampleClient;


    @Transactional(rollbackFor = Exception.class)
    public void doSomethingAndCallApi(String data) {
        // Save data to the database
        MyEntity entity = new MyEntity();
        entity.setData(data);
        myEntityRepository.save(entity);

        // Call an external API
        exampleClient.getExample();
        exampleClient.getExample2();

        myFeignClient.callApi();
        exampleClient.getExample();

        methodThatThrowsExceptions();
    }

    public void methodThatThrowsExceptions() {
        throw new RuntimeException("Random exception occurred");
        // Random random = new Random();
        //  if (random.nextBoolean()) {
        //      throw new RuntimeException("Random exception occurred");
        //   }
    }
}
