# atm-simulation
Simple ATM Simulation using Java Programming Language

## Stage 2

We were using a fixed set of accounts and keeping them into memory. As we're evolving our app, we'll need to adjust it for a few requirements

- Read the account initial info from a csv file
    - Fields will remain ` Name `, ` PIN `, ` Balance `, ` com.hafidsb.atm_simulation.models.Account Number `
- CSV file needs to have at least 20 records. Be creative :-)
- In order to read from CSV file, apply everything you've learned so far in this unit.
- We can safely assume that the CSV file will be on your disk, so you can pass an absolute path to read it
- Pass the file location as a program argument
- Add the following validations:
    - There can't be 2 different accounts with the same Account Number {{number}} (use this as a message)
    - There can't be duplicated records {{record}}(use this as a message and show the whole record)
    - When any of this exceptions happens show the corresponding message on screen

- We need to keep track of the transactions that have been made in our system. For that reason, model something that will help us with this requirement
- Provide a screen that help us query the last 10 transactions of a given account
- Use class/objects/methods names according to the DOMAIN. REFACTOR IF NECESSARY

# HINT!
You can make use of everything you've learned to this point. Special attention to Lambdas, Streams and I/O

# Review

Each person will randomly review at least two of other person’s work.

Follow the steps below:

- Run the application. Is the Welcome screen displayed?
- Does the application provide all the previous functionalities?
- Does the application provide the functionality to read a file from program arguments? (i.e. passing an absolute path)
- Does the app allow to transfer to any of the accounts loaded from the file?
- Does the app provide a way of reading a csv record into an account? How cluttered is the code?
- Are there at least 3 abstractions?
    - Expected here would be to have:
        - com.hafidsb.atm_simulation.models.Account class representation
        - Transaction class representation (along with some children like Withdraw, Transfer, Inquiry or anything that allows us to classify them)
        - Repository for reading from CSV file. A "pseudo"-DAO would be enough
        - Other domain classes are ok
- Does the code show any way of validating uniqueness of an account
- Does the app provide a way of querying last X transactions
- Does the app implement a way of reading the CSV file via Streams and I/O?


SIDE NOTE:
- I think the concurrency part will be a bit difficult to simulate or implement at this moment. Also, nowadays is very unlikely that someone will ever need to implement concurrency on a low-level.
  For example, Spring Boot provides already a ThreadPoolExecutor easily configurable for async tasks. Also, streams api provide tthe parallelStream method to allow the app process the operations in parallel. I think the concurrency could be evaluated on the Green Belt level
- Something that I'm expecting here is to have 2 kinds of stores, one for Accounts and one for Transactions,  that will act as very basic datasources. I would expect also an upper layer that will provide methods to operate over this "proto-stores". Such methods should be kept for stage3 as abstractions to operate with database




