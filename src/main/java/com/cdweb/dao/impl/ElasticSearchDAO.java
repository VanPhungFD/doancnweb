package com.cdweb.dao.impl;

import org.springframework.stereotype.Repository;

import com.cdweb.dao.IElasticSearchDAO;

@Repository
public class ElasticSearchDAO implements IElasticSearchDAO {
//	@Autowired
//	private ObjectMapper objectMapper;
//
//	RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
//
//	@Override
//	public List<Object> findAllProductByKeywords(String keywords) {
//		SearchRequest request = new SearchRequest();
//		request.indices("product_manage");
//		BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
//		//booleanQueryBuilder.must(QueryBuilders.functionScoreQuery(QueryBuilders.matchQuery("name", keywords))
//		//		.scoreMode(ScoreMode.SUM).boostMode(CombineFunction.SUM));
//		// QueryBuilder builder = QueryBuilders.multiMatchQuery(keywords,
//		// "name","image").type(MatchQuery.Type.PHRASE_PREFIX);
//		booleanQueryBuilder.must(QueryBuilders.functionScoreQuery(QueryBuilders.queryStringQuery(keywords).field("name"))
//				.scoreMode(ScoreMode.SUM).boostMode(CombineFunction.SUM));
//		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//		searchSourceBuilder.query(booleanQueryBuilder);
//		searchSourceBuilder.fetchSource(new String[] { "id", "name", "image", "unitPrice", "categoryid" }, null);
//		request.source(searchSourceBuilder);
//		List<Object> list = new ArrayList<>();
//		SearchResponse response = null;
//		try {
//			response = client.search(request, RequestOptions.DEFAULT);
//			SearchHits hit = response.getHits();
//			if (response.getHits().getTotalHits().value > 0) {
//				SearchHit[] searchHits = hit.getHits();
//				for (SearchHit searchHit : searchHits) {
//					// map nhan chuoi json
//
//					Map<String, Object> map = searchHit.getSourceAsMap();
//					System.out.println(map.toString());
//					list.add(objectMapper.convertValue(map, Product.class));
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return list;
//	}
//
//	@Override
//	public List<Object> findAllProductByCategory(Integer id) {
//		SearchRequest request = new SearchRequest();
//		request.indices("product_manage");
//		BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
//		booleanQueryBuilder.must(QueryBuilders.functionScoreQuery(QueryBuilders.matchQuery("categoryid", id)));
//		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//		searchSourceBuilder.from(0).size(30);
//		searchSourceBuilder.query(booleanQueryBuilder);
//		searchSourceBuilder.fetchSource(new String[] { "id", "name", "image", "unitPrice", "categoryid" }, null);
//		request.source(searchSourceBuilder);
//		List<Object> list = new ArrayList<>();
//		SearchResponse response = null;
//		try {
//			response = client.search(request, RequestOptions.DEFAULT);
//			SearchHits hit = response.getHits();
//			if (response.getHits().getTotalHits().value > 0) {
//				SearchHit[] searchHits = hit.getHits();
//				for (SearchHit searchHit : searchHits) {
//					Map<String, Object> map = searchHit.getSourceAsMap();
//					list.add(objectMapper.convertValue(map, Product.class));
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return list;
//	}
}
