# Task 1

Please, complete the following task: Multithreading Sorting via FJP

**1 point.**

> Implement Merge Sort or Quick Sort algorithm that sorts a huge array of integers in parallel using Fork/Join framework.

# Task 2

Please, complete the following task: Completable Future Helps to Build Open Salary Society

**2 points**

> Assume, we have REST endpoint that returns a list of hired Employees.
> * REST endpoint is wrapped by Java service class that consuming this endpoint.
> * Fetch a list of Employee objects asynchronously by calling the hiredEmployees().
> * Join another `CompletionStage<List>` that takes care of filling the salary for each hired employee, by calling the getSalary(hiredEmployeeId) method which returns a `CompletionStage` that asynchronously fetches the salary (again could be consuming a REST endpoint). 
> * When all Employee objects are filled with their salaries, we end up with a `List<CompletionStage>`, so we call `<special operation on CF>` to get a final stage that completes upon completion of all these stages.
> * Print hired Employees with their salaries via `<special operation on CF>` on the final stage.
> * Provide correct solution with CF usage and use appropriate CF operators instead `<special operation on CF>`. Why does the CF usage improve performance here in comparison with the synchronous approach? Discuss it with a mentor. How thread waiting is implemented in a synchronous world?

# Task 3 (Optional)

Please, complete the following task: Factorial via FJP

**1 point.**

> Use FJP to calculate the factorial. Compare with the sequential implementation. Use BigInteger to keep values.

# Task 4 (Optional)

Please, complete the following task: File Scanner via FJP

**1 point.**

> Create CLI application that scans a specified folder and provides detailed statistics:

1. File count.
2. Folder count.
3. Size (sum of all files size) (similar to Windows context menu Properties). Since the folder may contain a huge number of files the scanning process should be executed in a separate thread displaying an informational message with some simple animation like the progress bar in CLI (up to you, but I'd like to see that task is in progress).

> Once the task is done, the statistics should be displayed in the output immediately. Additionally, there should be the ability to interrupt the process by pressing some reserved key (for instance c). Of course, use Fork-Join Framework for implementation parallel scanning.

# Task 5 (Optional)

Please, complete the following task: PC Assembly Line with Akka

**3 points + 1 point for bonus task.**

> Automatize PC assembly line with Akka Framework (use Java API).

Requirements:

* Create PC object and fill it with data from operation to operation.
* Make more than 2 levels-hierarchy of actors.
* Log all steps and meta info about Self and Sender with AkkaLogger.
* Create 1 actor (or more) for each step of process and 1 router to route all requests for assembling.
* Write Service layer with CompletableFutures in signatures (handle router answers or inbox events) where it is possible.
* Provide client code to call Service layer methods (your choice).
* Try with different dispatchers and pick one for your solution.
* Tune ForkJoin executor for your environment (change configuration).
* The choice should be based on performance tests *
* Throughput of assembly line should be more than 100 000 assembled PCs per second *
* Throughput of assembly line should be more than 1 000 000 assembled PCs per second *
* - optional points (5 points per subtask).

An example of actions is described in [this paper](http://techreport.com/review/23624/how-to-build-a-pc-the-tech-report-guide).

