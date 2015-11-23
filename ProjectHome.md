This Project aims to incorporate the Metasploit Framework natively within Java, through JRuby. This will allow full reign over the Metasploit Framework through Java enabling other possibilities on the Java Platform.

The development of this Project is still on going. To get started you will require both Metasploit and JRuby. Both of which can be downloaded from:

  * http://www.metasploit.com/
  * http://www.jruby.org/

I personally recommend using subversion for either to keep up-to-date. Both will need to be extracted and placed in a folder along-side one another. The default directory is "/opt/jsploit/". However, you may want to bundle each within your own project.

There are two examples bundled with this library.

  * org.metasploit.test.Example.java
  * org.metasploit.test.ExploitExample.java
  * org.metasploit.test.AuxiliaryExample.java
  * org.metasploit.test.AutoPwnExample.java

The Example.java starts either the Metasploit CLI or Console. Whereas ExploitExample.java and AuxiliaryExample is a more hands-on aproach to the Metasploit Framework.

If you find any bugs please raise a ticket so that it can be addressed.