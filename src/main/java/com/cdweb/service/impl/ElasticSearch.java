package com.cdweb.service.impl;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpHost;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.ElasticsearchClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.index.reindex.DeleteByQueryRequestBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdweb.dao.IElasticSearchDAO;
import com.cdweb.entity.Product;
import com.cdweb.service.IElasticSearch;
import com.cdweb.service.IProductService;

@Service
public class ElasticSearch implements IElasticSearch {

	@Autowired
	private IProductService proService;

	@Autowired
	private IElasticSearchDAO elasticDAO;

	IndexRequest indexRequest = new IndexRequest("product_manage");
	DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");

	private RestHighLevelClient connect() {
		RestHighLevelClient client = new RestHighLevelClient(
				RestClient.builder(new HttpHost("localhost", 9200, "http"), new HttpHost("127.0.0.1", 9201, "http")));
		return client;
	}

	@Override
	public void documentElasticSearch() {
		for (Product product : proService.findAll()) {
			this.indexRequest.id(String.valueOf(product.getId()));
			System.out.println(product.getProductDate());
			this.indexRequest.source("id", product.getId(), "name", product.getName(), "image", product.getImage(),
					"unitPrice", product.getUnitPrice(), "categoryid", product.getCategory().getId(), "productDate",
					this.formatDate.format(product.getProductDate()));
			try {
				this.connect().index(this.indexRequest, RequestOptions.DEFAULT);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			this.connect().close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Object> searchElastiProduct(String keywords) {
		return elasticDAO.findAllProductByKeywords(keywords);
	}

	@Override
	public void documentElasticSearch(Product product) {
		this.indexRequest.id(String.valueOf(product.getId()));
		System.out.println(product.getProductDate());
		this.indexRequest.source("id", product.getId(), "name", product.getName(), "image", product.getImage(),
				"unitPrice", product.getUnitPrice(), "categoryid", product.getCategory().getId(), "productDate",
				this.formatDate.format(product.getProductDate()));

		try {
			this.connect().index(this.indexRequest, RequestOptions.DEFAULT);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Object> searchElasticProductByCate(Integer id) {
		return elasticDAO.findAllProductByCategory(id);
	}

	@Override
	public void updateElastic(Product p) {
		UpdateRequest request = new UpdateRequest(this.indexRequest.index(), String.valueOf(p.getId()));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", p.getId());
		map.put("name", p.getName());
		map.put("image", p.getUnitPrice());
		map.put("unitPrice", p.getUnitPrice());
		map.put("categoryid", p.getCategory().getId());
		map.put("productDate", this.formatDate.format(p.getProductDate()));
		request.doc(map);
		try {
			UpdateResponse response = this.connect().update(request, RequestOptions.DEFAULT);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteElastic(int id) {
		DeleteRequest request = new DeleteRequest(this.indexRequest.index(), String.valueOf(id));
	
			try {
				this.connect().delete(request, RequestOptions.DEFAULT);
			} catch (IOException e) {
				e.printStackTrace();
			}
	

	}

}
