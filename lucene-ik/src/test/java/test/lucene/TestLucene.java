package test.lucene;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class TestLucene {

	public static final String INDEX_DIR = "E:\\lucence\\novel";
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//createIndex();
		
		createIndex("E:\\文档\\小说\\橙红年代全文阅读\\chapters");
		searchIndex("银行");
	}

	/**
	 * demo1
	 * 
	 * 创建索引和读取索引的简单demo
	 * @throws Exception
	 */
	public static void createIndex() throws Exception{
		
		// 首先，我们需要定义一个词法分析器。
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_47);

		// 第二步，确定索引文件存储的位置，Lucene提供给我们两种方式：
		// 1 本地文件存储 
		// 2 内存存储
	    // Store the index in memory:
	    //Directory directory = new RAMDirectory();
	    // To store an index on disk, use this instead:
	    Directory directory = FSDirectory.open(new File(INDEX_DIR));
	    
	    // 第三步，创建IndexWriter，进行索引文件的写入。
	    // 这里的IndexWriterConfig，据官方文档介绍，是对indexWriter的配置，其中包含了两个参数，第一个是目前的版本，第二个是词法分析器Analyzer。
	    IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_47, analyzer);
	    IndexWriter iwriter = new IndexWriter(directory, config);
	    
	    // 第四步，内容提取，进行索引的存储。
	    Document doc = new Document();
	    String text = "This is the text to be indexed.";
	    doc.add(new Field("fieldname", text, TextField.TYPE_STORED));
	    iwriter.addDocument(doc);
	    iwriter.close();
	    
	    // Now search the index:
	    DirectoryReader ireader = DirectoryReader.open(directory);
	    IndexSearcher isearcher = new IndexSearcher(ireader);
	    // Parse a simple query that searches for "text":
	    QueryParser parser = new QueryParser(Version.LUCENE_47, "fieldname", analyzer);
	    Query query = parser.parse("text");
	    ScoreDoc[] hits = isearcher.search(query, null, 1000).scoreDocs;
	    //assertEquals(1, hits.length);
	    // Iterate through the results:
	    for (int i = 0; i < hits.length; i++) {
	      Document hitDoc = isearcher.doc(hits[i].doc);
	      System.out.println("This is the text to be indexed." + hitDoc.get("fieldname"));
		    //assertEquals("This is the text to be indexed.", hitDoc.get("fieldname"));
	    }
	    ireader.close();
	    directory.close();
	}
	
	/**
	 * demo1
	 * 
	 * 读取索引的简单demo
	 * @throws Exception
	 */
	public static void queryIndex() throws Exception{
		// 第一步，打开存储位置
		Directory directory = FSDirectory.open(new File(INDEX_DIR));
		DirectoryReader ireader = DirectoryReader.open(directory);
		
	    // 第二步，创建搜索器
		IndexSearcher isearcher = new IndexSearcher(ireader);
		
		// 首先，我们需要定义一个词法分析器。
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_47);
		
		// 第三步，类似SQL，进行关键字查询
		QueryParser parser = new QueryParser(Version.LUCENE_47, "fieldname", analyzer);
		Query query = parser.parse("text");
		ScoreDoc[] hits = isearcher.search(query, null, 1000).scoreDocs;
		// assertEquals(1, hits.length);
		for (int i = 0; i < hits.length; i++) {
		    Document hitDoc = isearcher.doc(hits[i].doc);
		    System.out.println("This is the text to be indexed." + hitDoc.get("fieldname"));
		    // assertEquals("This is the text to be indexed.",hitDoc.get("fieldname"));
		}
		
		// 第四步，关闭查询器等。
		ireader.close();
		directory.close();

	}
	
	/**
     * 创建当前文件目录的索引
     * @param path 当前文件目录
     * @return 是否成功
	 * @throws Exception 
     */
    public static boolean createIndex(String path) throws Exception{
        Date date1 = new Date();
        
        File fileDir = new File(path);
        File[] files = fileDir.listFiles();
        List<File> fileList = Arrays.asList(files);
        
        String content = null;
        for (File file : fileList) {
            content = "";
            //获取文件后缀
            String type = file.getName().substring(file.getName().lastIndexOf(".")+1);
            if("txt".equalsIgnoreCase(type)){
                
                content += txt2String(file);
            
            }
            
            System.out.println("name :"+file.getName());
            System.out.println("path :"+file.getPath());
//            System.out.println("content :"+content);
            System.out.println();
            
            
            try{
            	Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_47);
            	Directory directory = FSDirectory.open(new File(INDEX_DIR));
    
                File indexFile = new File(INDEX_DIR);
                if (!indexFile.exists()) {
                    indexFile.mkdirs();
                }
                IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_47, analyzer);
                IndexWriter indexWriter = new IndexWriter(directory, config);
                
                Document document = new Document();
                document.add(new TextField("filename", file.getName(), Store.YES));
                document.add(new TextField("content", content, Store.YES));
                document.add(new TextField("path", file.getPath(), Store.YES));
                indexWriter.addDocument(document);
                indexWriter.commit();
                
                // 关闭indexWriter
                indexWriter.close();
                
            }catch(Exception e){
                e.printStackTrace();
            }
            content = "";
        }
        Date date2 = new Date();
        System.out.println("创建索引-----耗时：" + (date2.getTime() - date1.getTime()) + "ms\n");
        return true;
    }
    
    /**
     * 查找索引，返回符合条件的文件
     * @param text 查找的字符串
     * @return 符合条件的文件List
     */
    public static void searchIndex(String text){
        Date date1 = new Date();
        try{
        	Directory directory = FSDirectory.open(new File(INDEX_DIR));
        	Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_47);
            DirectoryReader ireader = DirectoryReader.open(directory);
            IndexSearcher isearcher = new IndexSearcher(ireader);
    
            QueryParser parser = new QueryParser(Version.LUCENE_47, "content", analyzer);
            Query query = parser.parse(text);
            
            ScoreDoc[] hits = isearcher.search(query, null, 1000).scoreDocs;
        
            for (int i = 0; i < hits.length; i++) {
                Document hitDoc = isearcher.doc(hits[i].doc);
                System.out.println("____________________________");
                System.out.println(hitDoc.get("filename"));
                System.out.println(hitDoc.get("content"));
                System.out.println(hitDoc.get("path"));
                System.out.println("____________________________");
            }
            ireader.close();
            directory.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        Date date2 = new Date();
        System.out.println("查看索引-----耗时：" + (date2.getTime() - date1.getTime()) + "ms\n");
    }
    
    
    public static String txt2String(File file) throws Exception{
    	String chartset = "UTF-8";
		if(chartset == null || "".equals(chartset.trim())) {
			chartset = "UTF-8";
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), chartset));
		StringBuilder contentBuilder = new StringBuilder();
		String line = null;
		while((line = reader.readLine())!= null) {
			contentBuilder.append(line);
			contentBuilder.append("\n");
		}
		reader.close();
		return contentBuilder.toString();
    }
}
