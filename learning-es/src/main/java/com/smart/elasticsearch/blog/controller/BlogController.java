package com.smart.elasticsearch.blog.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.smart.elasticsearch.blog.domain.Blog;
import com.smart.elasticsearch.blog.param.SearchParam;
import com.smart.elasticsearch.blog.service.BlogJestService;
import com.smart.elasticsearch.core.result.SmartSearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class BlogController {

    @Autowired
    BlogJestService blogJestService;

    @ResponseBody
    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @ResponseBody
    @RequestMapping("/batchIndex")
    public String batchIndex(@RequestBody  List<Blog> blogList){
        //List<Blog> blogList = Lists.newArrayList();
        //for (int i = 0; i < 10; i++) {
        //    Blog blog = new Blog();
        //    blog.setAuthor("Martin.S"+i);
        //    blog.setCategroy("类目"+i);
        //    blog.setContent("Strom的Topology包含Spout和Bolt两种节点类型，在这个案例中，可以使用Spout来对数据源进行处理（模拟产生数据），\n" +
        //            "然后将其发送到计算和的Bolt中，所以实际上这里只需要使用一个Spout节点和一个Bolt节点就可以了。");
        //    blog.setCreateTime(System.currentTimeMillis());
        //    blog.setId(i+1);
        //    blog.setLastUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        //    blog.setTags(Lists.newArrayList("tag1","tag2"));
        //    blog.setTitle("标题"+i);
        //    blogList.add(blog);
        //}
        blogJestService.batchIndex("blog","blog",blogList);
        return "SUCESS";
    }

    @ResponseBody
    @RequestMapping("/queryBySearchParam")
    public SmartSearchResult<Blog> queryBySearchParam(@RequestBody  SearchParam searchParam){
        return blogJestService.queryBySearchParam(searchParam);
    }

    @ResponseBody
    @RequestMapping("/buildIndexMapping")
    public String buildIndexMapping(@RequestBody  String type){
        return blogJestService.buildIndexMapping(type);
    }
    @ResponseBody
    @RequestMapping("/deleteItem")
    public boolean deleteItem(@RequestBody  String id){
        return blogJestService.deleteItem("blog","blog",id);
    }
    @ResponseBody
    @RequestMapping("/queryAll")
    public SmartSearchResult<Blog> queryAll(){
        return blogJestService.queryAll("blog","blog",5,Blog.class);
    }
    @ResponseBody
    @RequestMapping("/queryById")
    public Blog queryById(@RequestBody  String id){
        return blogJestService.queryById("blog","blog",id,Blog.class);
    }
    @ResponseBody
    @RequestMapping("/singleIndex")
    public String singleIndex(@RequestBody  Blog blog){
        blogJestService.singleIndex("blog","blog",blog);
        return "SUCESS";
    }
    @ResponseBody
    @RequestMapping("/singleIndexWithId")
    public String singleIndexWithId(@RequestBody Blog blog){
        blogJestService.singleIndexWithId("blog","blog",blog.getId()+"",blog);
        return "SUCESS";
    }

}
