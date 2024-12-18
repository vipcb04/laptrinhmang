package util;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import model.bean.Documents;

public class QueueManager {
	private static final BlockingQueue<Documents> 	queue = new LinkedBlockingQueue<Documents>();
	public static void addDocument(Documents document)
	{
		queue.offer(document);
	}
	public static Documents getnextDocument() throws InterruptedException
	{
		return queue.take();
	}

}
