Sirma academy final project.

- The project should, given the of a worker, find how many days is his longest collaboration with a coworker on a single project. The program will return the id of the coworker and how many days they collaborated.
- As of now it has CRUD functionality for the Employee dto and WorkHistory dto.
-  DB used is mysql. Employee and WorkHistory are in a bidirectional relationship, with Employee being one to many and WorkHistory being many to one.
-  As of now it supports only the default LocalDate format in regards to dates.

  TODO
  - More complete validations.
  - Remove placeholder exception handling and add AOP handling of exceptions with ControllerAdvice
  - Implement functionality for more/all formats in regards to dates.

    TODO outside of the final exam
    - Finish Project dto and it's controller/service and it's relationship to the already existing tables/dtos
