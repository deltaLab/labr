labr
====

项目框架分前端和后端，后端跟前端属于松耦合。
前端与后端直接交互的数据格式，可以是任意通用格式，如json ，jsonp，xml，html等等.
这样做的好处是：后端处理数据可以用任意语言，比如C ，C++，Java，Javascript，Go，Python等。前端可以用任意的javascript框架，可以是原始的javascript，可以是jQuery，可以是YUI或者KISSY或者EXT等等。
其中后端使用的技术比较简单但是是基于Servlet3.0版本的，服务器最好使用tomcat7。JBoss好像不支持Servlet3.0版本。搭建环境最好是用java7以上版本(虽然java6已经支持Servlet3.0)。我使用的java版本是"1.7.0_45"。我使用的IDE是Eclipse IDE for Java EE Developers 4.2版本。还有就是，需要安装Ant，这跟版本关系不大，因为这里只使用了简单的功能，我装的是ant1.9.。我们主要用来复制文件
