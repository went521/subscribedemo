package com.went.springboot.convert;



/**
 * @author went
 * @version 1.0
 * @date 2022/5/23 12:56
 */
//public class WentMessageConverter implements HttpMessageConverter<Person> {
//
//    @Override
//    public boolean canRead(Class<?> clazz, MediaType mediaType) {
//        return false;
//    }
//
//    @Override
//    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
//        return clazz.isAssignableFrom(Person.class);
//    }
//
//    /**
//     * 服务器要统计所有MessageConverter都能写出哪些内容类型
//     *
//     * application/x-guigu
//     * @return
//     */
//    @Override
//    public List<MediaType> getSupportedMediaTypes() {
//        return MediaType.parseMediaTypes("application/x-went");
//    }
//
//    @Override
//    public Person read(Class<? extends Person> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
//        return null;
//    }
//
//    @Override
//    public void write(Person person, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
//        //自定义协议数据的写出
//        String data = person.getUserName()+";"+person.getAge()+";"+person.getBirth();
//
//
//        //写出去
//        OutputStream body = outputMessage.getBody();
//        body.write(data.getBytes());
//    }

