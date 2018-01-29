# Angular
Angular is a platform that makes it easy to build applications with the web. <br>
Angular combines declarative templates, dependency injection, end to end tooling, and integrated best practices to solve development challenges.  <br>
Angular empowers developers to build applications that live on the web, mobile, or the desktop


# Why not angularJS

**two-way binding**<br>
angularJS is easy to used, especially the two-way data binding makes a lot easier to rerender a changed value.

**performance problem**<br>
but it's brings the performance problem too, 
if the component tree excess 3 levels and redender more than a thousands of conponents at a time, the fps will desend very visibly.

because the angularJS change detector watchs the whole dom tree and all the states all the time, though a change is easy to be reached, a potential performance problem is always there.


# Why angular5

**component based**<br>
a best prectice for the component based development, especially in the frontend.<br>
a single component contains the template, css and ts logic, easy to maintain and easy to reuse.

i think mix the js logic and the html is not a good idea, it's hard to read when the component is complex, also hard to maintain. like the jsx way.

**performance promotion**<br>
angular5 use both one-way and two way binding, and several change detect strategy.<br>
it can lead a good performance to achieve a good fps, by default it 60 fps or respone a input action within 17 millisecond.


**dependency injection**<br>
dependency injection is an important application design pattern. It's used so widely that almost everyone just calls it DI.

the di means a service can be used all over the app and just declare as a provider, like a state service or a request service.

**service worker**<br>
the service worker also, but this can only be used in a https web app.

**3rd party library**<br>
angular is also easy to integrate with the 3rd party library like jquery, highcharts and datatables, it has a great material design too.

**ssr**<br>
angular has the ssr version too, but there are still some issue remained, like the window and document object can not exist on the server side, and the direct manipulation od dom will cause a error.


# end

I have used angular5 for about one ten months. the first release is just one year ago, and the first time i try this framework is angular2, and now it's 5.

now the angular is so powerful and so convenient, and it also got a great community and g great team to support of google.

so when i develop a web app, i chose the angular.

