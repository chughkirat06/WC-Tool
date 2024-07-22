# Challenge 1 - Write your own WC Tool

<p> The wc (word count) tool is a command-line utility in Unix-like operating systems that is used to display the number of lines, words, and characters in a file. </p>

## Common Options
<ul>
<li><code>-l</code> : Print the number of lines. </li>
<li><code>-w</code> : Print the number of words. </li>
<li><code>-c</code> : Print the number of bytes. </li>
<li><code>-m</code> : Print the number of characters. </li>
</ul>

## Commands
<code>ccwc -c test.txt</code> : Count bytes in the file. <br>
<code>ccwc -l test.txt</code> : Count lines in the file. <br>
<code>ccwc -w test.txt</code> : Count words in the file. <br>
<code>ccwc -m test.txt</code> : Count characters in the file. <br>
<code>ccwc test.txt</code> : Prints count of lines, words and bytes in the file. <br>
<code>cat test.txt | ccwc -flag</code> : Pipe the content of a file to the ccwc command. 

## Tests
The project includes unit tests to ensure the functionality of the tool. Tests are written using JUnit and Mockito.
