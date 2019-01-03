package test.lucene;

import java.io.File;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
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

	public static final String NOVEL_INDEX_DIR = "E:\\lucence\\novel";
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		createIndex();
	}

	
	public static void createIndex() throws Exception{
		
		// 首先，我们需要定义一个词法分析器。
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_47);

		// 第二步，确定索引文件存储的位置，Lucene提供给我们两种方式：
		// 1 本地文件存储 
		// 2 内存存储
	    // Store the index in memory:
	    //Directory directory = new RAMDirectory();
	    // To store an index on disk, use this instead:
	    Directory directory = FSDirectory.open(new File(NOVEL_INDEX_DIR));
	    
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
	
	public static void queryIndex() throws Exception{
		// 第一步，打开存储位置
		Directory directory = FSDirectory.open(new File(NOVEL_INDEX_DIR));
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
}
