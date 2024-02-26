# Home Challence

## Before start: an extra implementation 
_If you don't want to configure the local environment to execute all test, you can execute everything using the GitHub Actions. I have created a pipeline to execute all tests under demand. Or also, when some Push/Pull request is in progress. For more details the gradle.yml configuration is at "https://github.com/ivanarroyave/HomeChallenge/blob/trunk/.github/workflows/gradle.yml"._

_**I recommend you make a Fork of the repository**. Next, go to https://github.com/[YOUR_GIT_HUB_USER_HERE]/HomeChallenge/actions and **select** the workflow called "Java CI con Gradle y Docker". Finally, do click on **Run workflow**._

_Here is a sample of my executions: https://github.com/ivanarroyave/HomeChallenge/actions/runs/8041728690_

_At the end of the page you cant download the report evidences!_

## First task: API test automation

_This first part is dedicated to testing RESTful API web services. The test automations are developed for the "Swagger Petstore Sample" project described at the web address https://github.com/swagger-api/swagger-petstore. The project was developed with Serenity-BDD using the Screenplay pattern._

_If you want to adapt the work environment locally, use the following group of technologies:_
```
Java 17+
InteliJ IDEA (plugins: Gherkin; Cucumber for Java; Substeps Intellij plugin, Gradle; Lombok)
Gradle wrapper 7.6.4
Git
Doker
```

### Local adaptation of the automation project
#### Clone the repository

_First of all, you must clone the repository on your computer._

```
git clone https://github.com/ivanarroyave/HomeChallenge.git
```
_The project of interest is in the path "\apiTestAutomation\swaggerPetstore". There you can use the "build.gradle.kts" file for open the project with IntelliJ._ 

#### Prepare Docker environment
Download and run the image.

```
docker pull swaggerapi/petstore3:unstable
docker run  --name swaggerapi-petstore3 -d -p 8080:8080 swaggerapi/petstore3:unstable
```

Once started, you can navigate to http://localhost:8080/api/v3/openapi.json to view the Swagger Resource Listing. This tells you that the server is up and ready to demonstrate Swagger.

#### Test execution
_There are two runners in the path "apiTestAutomation/swaggerPetstore/src/test/java/com/paloit/runners". One allows you to run the tests with Junit 4 and the other with Junit 5._

```
* RunnerTestsJunit4.
* RunnerTestsJunit5.
```
#### Execution of tests through the terminal

_Make sure you are located at the root of the project; in this case, in the path "apiTestAutomation/swaggerPetstore"._

```
./gradlew clean build -x test
./gradlew test --tests *runners* aggregate -i
```

_When the execution finishes you will see the status of the tests and also the access link to the Serenity-BDD report._

```
------------------------------------------------
| SERENITY TESTS:               | SUCCESS
------------------------------------------------
| Test scenarios executed       | 10
| Total Test cases executed     | 12
| Tests passed                  | 12
| Tests failed                  | 0
| Tests with errors             | 0
| Tests compromised             | 0
| Tests aborted                 | 0
| Tests pending                 | 0
| Tests ignored/skipped         | 0
------------------------------- | --------------
| Total Duration| 3s 481ms
| Fastest test took| 054ms
| Slowest test took| 2s 860ms
------------------------------------------------

SERENITY REPORTS
  - Full Report: file:///C:/Users/someUser/Desktop/HomeChallenge/apiTestAutomation/swaggerPetstore/target/site/serenity/index.html

```
### What was automated?

_Various services in the "Pet" and "Store" sections are automated._

#### Pet (For more details about scenarios, see the behavior definitions in the .feature files: apiTestAutomation/swaggerPetstore/src/test/resources/features/pet)
```
* Add a new pet to the store: http://localhost:8080/api/v3/pet
* Update an existing pet by Id: http://localhost:8080/api/v3/pet
* Finds Pets by status: http://localhost:8080/api/v3/pet/findByStatus
* Finds Pets by Tags: http://localhost:8080/api/v3/pet/findByTags
* Deletes a pet: http://localhost:8080/api/v3/pet/{petId}
```

#### Store (For more details about scenarios, see the behavior definitions in the .feature files: apiTestAutomation/swaggerPetstore/src/test/resources/features/store)
```
* Returns pet inventories by status: http://localhost:8080/api/v3/store/inventory
```




## Second task: API performance test
_This second part is dedicated to testing RESTful API web services. The test automations are developed for the "Swagger Petstore Sample" project described at the web address https://github.com/swagger-api/swagger-petstore. The project was developed with K6 https://k6.io/ If you wish to learn more about it, follow the docs https://k6.io/docs/._

_If you want to adapt the work environment locally, use the following group of technologies:_
```
k6
JavaScript
Visual Studio Code (or another code editor of your choice)
Git
```

### Local adaptation of the automation project
#### Clone the repository

_First of all, you must clone the repository on your computer._

```
git clone https://github.com/ivanarroyave/HomeChallenge.git
```
_The project of interest is in the path "\apiPerformanceImplementation\swaggerPetstore". There you can find subdirectories with differentes scripts. Use Visual Studio Code or other code editor to open them._ 

#### Prepare Docker environment
Download and run the image.

```
docker pull swaggerapi/petstore3:unstable
docker run  --name swaggerapi-petstore3 -d -p 8080:8080 swaggerapi/petstore3:unstable
```

Once started, you can navigate to http://localhost:8080/api/v3/openapi.json to view the Swagger Resource Listing. This tells you that the server is up and ready to demonstrate Swagger.

#### Execution of tests through the terminal
_Open the Terminal an make sure you are located at the path of the script of interest before initiating a run command with k6._

_If you just want execute and wait the result on the terminal._

```
k6 run FindPetsByStatus.js
k6 run AddNewPetToTheStore.js
```

_If you just want execute and generate a file with the results._
```
k6 run FindPetsByStatus.js > FindPetsByStatusResult.txt
k6 run AddNewPetToTheStore.js > AddNewPetToTheStoreResult.txt
```

_When the execution finished you will see the status of the tests. Some things like this:_

```

          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: AddNewPetToTheStore.js
     output: -

  scenarios: (100.00%) 1 scenario, 1000 max VUs, 4m30s max duration (incl. graceful stop):
           * default: Up to 1000 looping VUs for 4m0s over 3 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (0m01.0s), 0005/1000 VUs, 0 complete and 0 interrupted iterations
default   [   0% ] 0005/1000 VUs  0m01.0s/4m00.0s

running (0m02.0s), 0009/1000 VUs, 5 complete and 0 interrupted iterations
default   [   1% ] 0009/1000 VUs  0m02.0s/4m00.0s

running (0m06.0s), 0025/1000 VUs, 63 complete and 0 interrupted iterations
default   [   2% ] 0025/1000 VUs  0m06.0s/4m00.0s

...

running (2m01.0s), 0631/1000 VUs, 33675 complete and 0 interrupted iterations
default   [  50% ] 0631/1000 VUs  2m01.0s/4m00.0s

...
running (3m56.0s), 0071/1000 VUs, 81941 complete and 0 interrupted iterations
default   [  98% ] 0071/1000 VUs  3m56.0s/4m00.0s

running (3m57.0s), 0057/1000 VUs, 82012 complete and 0 interrupted iterations
default   [  99% ] 0057/1000 VUs  3m57.0s/4m00.0s

running (4m00.0s), 0005/1000 VUs, 82130 complete and 0 interrupted iterations
default   [ 100% ] 0005/1000 VUs  4m00.0s/4m00.0s

     ✗ is status 200
      ↳  0% — ✓ 703 / ✗ 81432
     ✗ is status not 500
      ↳  0% — ✓ 703 / ✗ 81432
     ✗ has a reasonable response time
      ↳  94% — ✓ 77508 / ✗ 4627
     ✗ time to first byte is short
      ↳  93% — ✓ 76958 / ✗ 5177

     checks.........................: 47.44% ✓ 155872     ✗ 172668
     data_received..................: 39 MB  163 kB/s
     data_sent......................: 25 MB  105 kB/s
     http_req_blocked...............: avg=9.38µs   min=0s    med=0s     max=9.1ms  p(90)=0s     p(95)=0s     
     http_req_connecting............: avg=7.44µs   min=0s    med=0s     max=9.1ms  p(90)=0s     p(95)=0s     
     http_req_duration..............: avg=379.77ms min=525µs med=6.23ms max=18.43s p(90)=42ms   p(95)=2.16s  
       { expected_response:true }...: avg=2.09ms   min=525µs med=2.02ms max=4.56ms p(90)=2.72ms p(95)=2.84ms 
     http_req_failed................: 99.14% ✓ 81432      ✗ 703   
     http_req_receiving.............: avg=45.44µs  min=0s    med=0s     max=2.99ms p(90)=42µs   p(95)=516.2µs
     http_req_sending...............: avg=8.88µs   min=0s    med=0s     max=2ms    p(90)=0s     p(95)=0s     
     http_req_tls_handshaking.......: avg=0s       min=0s    med=0s     max=0s     p(90)=0s     p(95)=0s     
     http_req_waiting...............: avg=379.71ms min=525µs med=6.19ms max=18.43s p(90)=42ms   p(95)=2.16s  
     http_reqs......................: 82135  341.167053/s
     iteration_duration.............: avg=1.38s    min=1s    med=1.01s  max=19.43s p(90)=1.04s  p(95)=3.16s  
     iterations.....................: 82135  341.167053/s
     vus............................: 5      min=5        max=1000
     vus_max........................: 1000   min=1000     max=1000


running (4m00.7s), 0000/1000 VUs, 82135 complete and 0 interrupted iterations
default ✓ [ 100% ] 0000/1000 VUs  4m0s

```
### What was automated?

_Some services in the "Pet" sections are automated. Both scripts are designed to perform load and stress tests on a specific endpoint of an API._

_This k6 scripts simulates an increasing and decreasing load scenario on a service, sending POST/GET requests to evaluate how the system handles the load in terms of request success and response times. Checks built into the script allow you to measure the quality of the server response under different levels of stress._

#### Pet
```
* Add a new pet to the store: http://localhost:8080/api/v3/pet
* Finds Pets by status: http://localhost:8080/api/v3/pet/findByStatus
```

### How to interpret a k6 report?
_Remember the report seen before._

```
Add a new pet to the store:(POST) http://localhost:8080/api/v3/pet
```

```
     ✗ is status 200
      ↳  0% — ✓ 703 / ✗ 81432
     ✗ is status not 500
      ↳  0% — ✓ 703 / ✗ 81432
     ✗ has a reasonable response time
      ↳  94% — ✓ 77508 / ✗ 4627
     ✗ time to first byte is short
      ↳  93% — ✓ 76958 / ✗ 5177

     checks.........................: 47.44% ✓ 155872     ✗ 172668
     data_received..................: 39 MB  163 kB/s
     data_sent......................: 25 MB  105 kB/s
     http_req_blocked...............: avg=9.38µs   min=0s    med=0s     max=9.1ms  p(90)=0s     p(95)=0s     
     http_req_connecting............: avg=7.44µs   min=0s    med=0s     max=9.1ms  p(90)=0s     p(95)=0s     
     http_req_duration..............: avg=379.77ms min=525µs med=6.23ms max=18.43s p(90)=42ms   p(95)=2.16s  
       { expected_response:true }...: avg=2.09ms   min=525µs med=2.02ms max=4.56ms p(90)=2.72ms p(95)=2.84ms 
     http_req_failed................: 99.14% ✓ 81432      ✗ 703   
     http_req_receiving.............: avg=45.44µs  min=0s    med=0s     max=2.99ms p(90)=42µs   p(95)=516.2µs
     http_req_sending...............: avg=8.88µs   min=0s    med=0s     max=2ms    p(90)=0s     p(95)=0s     
     http_req_tls_handshaking.......: avg=0s       min=0s    med=0s     max=0s     p(90)=0s     p(95)=0s     
     http_req_waiting...............: avg=379.71ms min=525µs med=6.19ms max=18.43s p(90)=42ms   p(95)=2.16s  
     http_reqs......................: 82135  341.167053/s
     iteration_duration.............: avg=1.38s    min=1s    med=1.01s  max=19.43s p(90)=1.04s  p(95)=3.16s  
     iterations.....................: 82135  341.167053/s
     vus............................: 5      min=5        max=1000
     vus_max........................: 1000   min=1000     max=1000


running (4m00.7s), 0000/1000 VUs, 82135 complete and 0 interrupted iterations
default ✓ [ 100% ] 0000/1000 VUs  4m0s
```
_A brief interpretation:_

_**The scenario:** A single scenario was executed with up to 1000 virtual users (VUs) over a total period of 4 minutes, distributed in three stages. The gradual increase in users followed by a decrease is designed to simulate a load spike on the system.
Completed Iterations: 82,135 iterations were completed without interruptions, indicating many requests were sent to the test endpoint._

_**Completed Iterations:** 82,135 iterations were completed without interruptions, indicating many requests were sent to the test endpoint._

_**HTTP Status Checks:** 81432 request failed with statuses other than 200, suggesting that the majority of server responses were not successful. Only 703 requests were successful (is status 200 and is status not 500)._

_**Response Times:** While 94% of requests had what was considered a reasonable response time and 93% had a short time to the first byte, the average wait times (http_req_waiting) and total request duration (http_req_duration) were high, averaging around 379.77 ms with peaks up to 18.43 seconds._

_**Summary:** The test shows that under heavy load, the system struggles to efficiently handle a high number of concurrent requests. The vast majority of requests resulted in failures, which may indicate performance or stability issues with the tested system._


## Author

_**Ivan Dario Arroyave Arboleda** - Systems engineer - Specialized in software quality assurance - Github user: [ivanarroyave](https://github.com/ivanarroyave)_

## Expressions of Gratitude?
* Tell others about this project.
* Invite someone on the team a beer or coffee.
* Give thanks publicly.
* etc.