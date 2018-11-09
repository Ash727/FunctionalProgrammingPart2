package com.teamtreehouse.jobs;

import com.google.api.client.util.DateTime;
import com.teamtreehouse.jobs.model.Job;
import com.teamtreehouse.jobs.service.JobService;

import java.net.HttpURLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class App {

  public static void main(String[] args) {
    JobService service = new JobService();
    boolean shouldRefresh = false;
    try {
      if (shouldRefresh) {
        service.refresh();
      }
      List<Job> jobs = service.loadJobs();
      System.out.printf("Total jobs:  %d %n %n", jobs.size());
      explore(jobs);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void explore(List<Job> jobs) {
      // Your amazing code below..

      // printPortLandJobsStream(jobs);
      //   getThreeJuniorJobsStream(jobs).forEach(System.out::println);
      //  getaCaptionsImperatively(jobs).forEach(System.out::println);
      //   getCaptionStream(jobs).forEach(System.out::println);
//  Stream.of("this", "is","a","stream").forEach(System.out::println);
//    getSnippetWordCountsImperatively(jobs).forEach((key,value)->System.out.printf("%s,occurs %d times %n", key ,value));
//    getSnippetWordCountsStream(jobs).forEach((key,value)->System.out.printf("%s,occurs %d times %n", key ,value));
//  System.out.println(  IntStream.of(1,2,3,4)
//            .filter( i -> i<4)
//            . sum()
//    );
      //===================== Gives the name with max number of length name===================================================
//  System.out.println(
//    jobs.stream()
//          .map(Job::getCompany)
////          .mapToInt(companyName -> companyName.length())
////              .max(Comparator.comparingInt(company-> company.length() ))
//            // if company 1 gives a negative its lower
//            // if company 1 is equal itll give 0
//            //// if it gives a positive number itll be greater than
//          .max((company1,company2)->company1.length() - company2.length())

//    );
      //===================== Gives the name with max number of length name===================================================


      // ===================== Im feeling lucky search


//     String searchTerm  = "Java";
//     Optional<Job> foundJob =  luckySearchJob(jobs, searchTerm);
////     foundJob.ifPresent(job->
////             System.out.println(foundJob.get().getTitle())
////             );
//
////    if(foundJob.isPresent()){
////            System.out.println(foundJob.get().getTitle());
////
////    }
////
////      else {
////        System.out.println("No jobs found");
////    };
//    // or
//      foundJob.map(Job::getTitle)
//              .orElse("No jobs found");


//     System.out.println(foundJob);
      // ===================== Im feeling lucky search

      // ============Ranges and using disticnt
      // Example of limited search
// --- Search job via letter
// sortJobByletter(job, "N");

      // ===== chainPredicateExample

      // echecks that two predicats and sets them up indvidually
      // remember predicates are like boolean for each iteam or item for
      // lambda function
//    chainPredicateExample(jobs);

      //=========
      // takes in a string and return or parse it into  a local date time
      // Then converts it into the correct format of local string
      ///=========
//      Function <String, LocalDateTime> indeedDateConverter =
//               dateString -> LocalDateTime.parse(dateString, DateTimeFormatter.RFC_1123_DATE_TIME);
//
//      Function<LocalDateTime, String > siteDAteStringConverter =
//              date-> date.format(DateTimeFormatter.ofPattern(("M/d/YY")));
//      Function<String, String> indeedToSiteDateStringConverter =
//              indeedDateConverter.andThen(siteDAteStringConverter);
//
      //=========
      // takes in a string and return or parse it into  a local date time
      // Then converts it into the correct format of local string
      ///=========



      jobs.stream()
              .map(Job::getDateTimeString)
              .limit(8)
              .forEach(System.out::println);

      //=========
      // takes in a string and return or parse it into  a local date time
      // Then converts it into the correct format of local string
      ///=========
  }

  public static void exampleWithArrays (List<Job> jobs) {

          int [] apples =  {0,9,1,8};
//   apples = {0,8,1/*/,9};

          int [] apples2 = Arrays.stream(apples).filter(number -> number<4).toArray();
          for (int apple:apples2){
              System.out.println(apple);
          }
          Stream.of(jobs).forEach(System.out::println);
//   System.out.println(apples2);
//  Stream.of(apples).forEach(System.out::println);

      }


  // not this method returns Function recieving a string and returning a string
  public static Function<String, String> createDateStringconveter(
          DateTimeFormatter inFormatter, DateTimeFormatter outFormatter) {
      // returning a function that takes in a string and returns a string
            return dateString -> {
                return LocalDateTime.parse(dateString,inFormatter)
                        .format(outFormatter);
            };

    }

  public static void chainPredicateExample(List<Job> jobs){
    Job firstOne = jobs.get(0);
    System.out.println("First job"+ firstOne);
    // remember predicate work like true or false
    Predicate<Job> Calijobchecker = job -> job.getState().equals("CA");

    Job caJob = jobs.stream()
            .filter(Calijobchecker)
            .findFirst()
            .orElseThrow(NullPointerException::new);

    emailIfMatches(firstOne,Calijobchecker);
    // Becasuse of the and chaning predicate wont print anything
    emailIfMatches(caJob,Calijobchecker.and(App::isJuniorJob));

  }

   ///
  public static void sortJobByletter(List<Job> jobs, String letter){
    List<String> companies = jobs.stream()
            .map(Job::getCompany)
            .distinct() // only gives a single occurance of the name
            .sorted()
            .collect(Collectors.toList());

    companies.stream()
            .filter(company -> company.startsWith(letter))
            .sorted((company,compan2) -> company.compareToIgnoreCase(compan2))
            .forEach(System.out::println);


  }

  // predicate liik boolean
  public static void emailIfMatches (Job job, Predicate<Job> checker)
  {
    if (checker.test(job)){
      System.out.println("I am sending an email about"+job);
    }
  }

  private static void LimitedSearch( List<String> companies) {
    int pageSize = 20;
    int numPages = companies.size() / pageSize;
    // Will generate an infintate stream of values
    // takes the last value and runs it through a function
    IntStream.iterate(1, i-> i + pageSize)
            .mapToObj(i-> String.format("%d.%s ", i, companies.get(i)))
            .limit(numPages)// limit is needed for the last variable
            .forEach(System.out::println);
  }

  public static void displayCompaniesMenuUsingRange(List<String> companies){

    // exclusivell
IntStream.rangeClosed(1,20)
        .mapToObj(i->String.format("%d.%s %n", i+1, companies.get(i-1)))
        .forEach(System.out::println);


}

  private static void displayCompaniesMenuImperatively(List<String> companies) {
    for(int i = 0 ;i<20; i++){
      System.out.printf("%d.%s",i+1, companies.get(i));
      System.out.println();
    }
  }

  private static Optional<Job> luckySearchJob(List<Job> jobs, String searchTearm){
  return jobs.stream()
          .filter(job -> job.getTitle().contains(searchTearm))
          .findFirst();
  }

  private static boolean isJuniorJob(Job job){

    // note that toLowerCAse is needed for the object
    return  job.getTitle().toLowerCase().contains("junior")|| job.getTitle().toLowerCase().contains("jr");
  }



  public static Map<String,Long> getSnippetWordCountsStream(List<Job> jobs){
    return jobs.stream()

            // we map on to every element and get the snippet return
            .map(Job::getSnippet)
            // every snippet returned we split every word in to array
            .map(snippet-> snippet.split("\\W+"))// anything that is not a word split
            // now we have an array of words for each job so we flattent them into one stream
            .flatMap(words -> Stream.of(words)) // anythingn out of here is like a brand new stream
            .filter(word-> word.length()>0)
            .map(String::toLowerCase)
            .collect(Collectors.groupingBy(
                    // key
                    Function.identity(), // word-> word, get a word return a word
                    // first
                    Collectors.counting() //the word that is returned is counted and grouped by the first parameter

            ));
  }


  // Remember a steream is like each of the memebers with in a gourp
  // is like a marvel traveling down our contraption
  // or course we set up
  private static List<Job> getThreeJuniorJobsStream(List<Job> jobs){
      return jobs.stream()
              .filter(App::isJuniorJob) // Short cut method to jobs -> isjuniorjob(jobs)
              .limit(3)
              .collect(Collectors.toList());

  }



  private static List<String> getCaptionStream(List<Job> jobs){
    return jobs.stream()
            .filter(App::isJuniorJob)
            .map(job -> job.getCaption())
            .limit(3)
            .collect(Collectors.toList());

  }


  private static List<String> getaCaptionsImperatively (List<Job> jobs){
    List<String> captions = new ArrayList<>();
    for (Job job : jobs){
      String caption = String.format("%s is looking for a  %s in %s",
              job.getCompany(),
              job.getTitle(),
              job.getCity());
      if(isJuniorJob(job)){
        captions.add(job.getCaption());
        if(captions.size()>=3){
          break;
        }
      }
    }
    return captions;
  }
  private static List<Job> getThreeJuniorJobsImperatively (List<Job> jobs){

    List<Job> juniorJobs = new ArrayList<>();
    for (Job job : jobs){
      String title = job.getTitle().toLowerCase();
      if(title.contains("junior")|| title.contains("jr")){
        juniorJobs.add(job);
        if(juniorJobs.size()>=3){
          break;
        }
      }
    }
    return juniorJobs;
  }



  public static Map<String, Long> getSnippetWordCountsImperatively(List<Job> jobs) {

    Map<String, Long> wordCounts = new HashMap<>();

    for (Job job : jobs) {
      String[] words = job.getSnippet().split("\\W+");
      for (String word : words) {
        if (word.length() == 0) {
          continue;
        }
        String lWord = word.toLowerCase();
        Long count = wordCounts.get(lWord);
        if (count == null) {
          count = 0L;
        }
        wordCounts.put(lWord, ++count);
      }
    }
    return wordCounts;
  }


  private static void printPortLandJobsStream(List<Job> jobs) {
    jobs.stream()
            .filter(job->job.getState().equals("OR"))
            .filter(job -> job.getCity().equals("Portland"))
            .forEach(System.out:: println);
  }
}
