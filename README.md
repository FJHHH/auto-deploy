# auto-deploy
这是一个上传jar包并部署的小demo

## how-to
cd到demo模块中，运行：
```
gradle jar
```

demo模块中打出的jar用于上传

运行主模块，使用postman或者其他工具，上传jar包：
![](img/upload-jars.png)

然后请求demo中的controller，不过要增加一个header:
![](img/demo.png)


