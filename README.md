## Title
CS222 First project

## Authors
Nicholas Newlin 
Hunter Johns

## Summary
This Java project demonstrates the ability to connect to Wikipedia's servers to fetch the most recent
editors of an article. The program prompts for an article title, and will display up to the last 13 edits in reverse
chronological order. If there is a redirect, the program will display where it redirected to. If there is a network
error, the user gives invalid input, or no article data could be found, the program returns an error message.

## Build Requirements
We used several external libraries as shown in the build.gradle file. Java 11 and
up is required to run this project.
In order to run the console version of the code, run the main method in the main class. In order to use the GUI iteration, use the gradle function "run".

## Functional Requirements
As an investigative journalist, I want to see who most recently changed a Wikipedia article.
1.  I can provide the name of a Wikipedia article on the command-line when I run the application.
    The system responds by providing the 13 most recent changes to that page, showing, in reverse-
    chronological order, the time of the change (using ISO 8601 date and time in UTC), one space
    character,and then the username of the person who made the change, followed by a newline character. 
    After printing the list of changes, exit the program.
2.  If there are fewer than 13 changes in the history of the page, show all the changes.
3.  If I was redirected as part of my search, then the first line of output from the application should
    have the form “Redirected to article name”. For example, a search for “Zappa” redirects to “Frank Zappa,”
    so the result would start with the line “Redirected to Frank Zappa”.
4.  If  I  do not provide  an article name on  the command-line, print an appropriate message to
    System.err and exit the program.
5.  If there is no Wikipedia page for the article  name I provide, print an appropriate message to
    System.err and exit the program.
6.  If there is a network error, print an appropriate error message to System.err  and exit the
    program.

## Sources used
Sections of code were repurposed from Paul Gestwicki's example found here:
https://www.youtube.com/watch?v=Ek06EI2yQOQ&ab_channel=PaulGestwicki

Sections of code were repurposed from thenewboston's example code found here:
https://www.youtube.com/watch?v=7LxWQIDOzyE&list=PL6gx4Cwl9DGBzfXLWLSYVy8EbTdpGbUIG&index=4