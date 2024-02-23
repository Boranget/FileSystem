- 文件内容存储方式
存储文件内容暂时使用了mysql中自带的longblob类型，该类型最大可存储4g的内容
- mybatis在xml文件中，在一个标签中执行多条sql语句报错，最后在jdbc的链接中添加参数&allowMultiQueries=true允许多语句执行
- 将输入流转为字节数组输出流
使用了ByteArrayOutputStream.toByteArray()方法
- 输入流的available与报文大小不同
  使用inputStream.available()并不总是返回整个流的大小。它只返回可以无阻塞地从此输入流读取（或跳过）的字节数的估计值。
  在实际应用中，这可能导致只读取了部分数据。
  获取不到完整的请求体内容是因为HTTP请求的正文（body）长度是动态的，而inputStream.available()方法返回的是在不阻塞的情况下可以从输入流中读取的字节数。当输入流中的数据量少于或等于输入流内部的缓冲区大小时，available()方法可能返回的是小于实际数据量的值，这就会导致您只读取了部分数据。
  为了完整地读取HTTP请求的正文内容，需要使用一个循环来不断读取输入流直到没有更多数据可读。
- tomcat对文件传输大小有限制
修改：
```yaml
# 上传文件大小限制
spring:
  servlet:
    multipart:
      max-file-size: 4GB
      max-request-size: 4GB
```