labr
====

项目框架分前端和后端，后端跟前端属于松耦合。
前端与后端直接交互的数据格式，可以是任意通用格式，如json ，jsonp，xml，html等等.
这样做的好处是：后端处理数据可以用任意语言，比如C ，C++，Java，Javascript，Go，Python等。前端可以用任意的javascript框架，可以是原始的javascript，可以是jQuery，可以是YUI或者KISSY或者EXT等等。
后端使用的技术比较简单但是是基于Servlet3.0版本的，服务器最好使用tomcat7。JBoss好像不支持Servlet3.0版本。搭建环境最好是用java7以上版本(虽然java6已经支持Servlet3.0)。我使用的java版本是"1.7.0_45"。我使用的IDE是Eclipse IDE for Java EE Developers 4.2版本。还有就是，需要安装Ant，这跟版本关系不大，因为这里只使用了简单的功能，我装的是ant1.9.。我们主要用来复制文件。
前端主要是css+html+js的形式，脚本目前使用的是jQuery。

进入项目的流程是
1.浏览器访问项目根目录
2.web.xml定向到index.jsp。
3.任何请求都会被FileRouter这个过滤器拦截到，FileRouter做的事是，把请求拦截到，把浏览器信息保存到session中，并且若是Servlet请求，需要重定向到项目根目录请求下。
4.若是访问的是index.jsp则jsp负责在对应浏览器目录下生成index.html页面，并重定向到html页面。这样主要是解决不同浏览器的css问题，尤其是手机浏览器，部分手机浏览器对css走样严重，有新的需要单独处理的浏览器，需要在FileRouter类里面加入判断。同时每个浏览器目录下面js可能是一样也可能不一样。若需要差异化处理，这个可以根据session里面存放的浏览器信息进行，先判断后处理。
5.利用jQuery使用Ajax的方式进行访问，若需要支持前进后退，需要在发送请求后，更改javascript的History对象里面的值。
