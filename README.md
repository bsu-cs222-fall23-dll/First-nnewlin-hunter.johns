## Title
CS222 First project

## Authors
Nicholas Newlin
Hunter Johns

Sections of code were repurposed from Paul Gestwicki's example found here:
https://www.youtube.com/watch?v=Ek06EI2yQOQ&ab_channel=PaulGestwicki

## Summary
This Java project demonstrates the ability to connect to Wikipedia's servers to fetch the most recent
editors of an article.

## Functional Requirements
As an investigative journalist, I want to see who most recently changed a Wikipedia article.
1.  I can provide the name of a Wikipedia article on the command-line when I run the application.
    The system responds by providing the 13 most recent changes to that page, showing, in reverse-
    chronological order, the time of the change (using ISO 8601 date and time in UTC), one space character, and then the username of the person who made the change, followed by a newline character. After printing the list of changes, exit the program.
2.  If there are fewer than 13 changes in the history of the page, show all the changes.
3.  If I was redirected as part of my search, then the first line of output from the application should
    have the form “Redirected to article name”. For example, a search for “Zappa” redirects to “Frank Zappa,” so the result would start with the line “Redirected to Frank Zappa”.
4.  If  I  do not provide  an article name on  the command-line, print an appropriate message to
    System.err and exit the program.
5.  If there is no Wikipedia page for the article  name I provide, print an appropriate message to
    System.err and exit the program.
6.  If there is a network error, print an appropriate error message to System.err  and exit the
    program.
