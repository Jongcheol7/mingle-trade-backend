package com.mingletrade.mingletrade.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.mingletrade.mingletrade.domain.PrevClosingPrice;
import com.mingletrade.mingletrade.mapper.PrevClosingPriceMapper;

@Service
public class PrevClosingPriceService {

	private final PrevClosingPriceMapper mapper;
	//RestTemplete: Spring이 클라이언트가 되어 외부 API서버에 HTTP 요청을 보낼때 사용
	private final RestTemplate restTemplate = new RestTemplate();
	
	public PrevClosingPriceService(PrevClosingPriceMapper mapper) {
		super();
		this.mapper = mapper;
	}
	
	@Transactional
	public void insert(PrevClosingPrice price) {
		price.setCloseDate(null);
		mapper.insertPrevClosingPrice(price);
	}
	
	@Transactional(readOnly = true)
	public List<PrevClosingPrice> selectAll(String closeDate){
		return mapper.selectPrevClosingPriceAll(closeDate);
	}
	
	// Binance API에서 전일 종가를 받아온후 DB에 저장
	@Transactional
	public void fetchAndInsertFromBinance() {
		// 1.모든 정보 가져오기
		String infoUrl = "https://api.binance.com/api/v3/exchangeInfo?permissions=SPOT";
		Map<String, Object> infoResponse = restTemplate.getForObject(infoUrl, Map.class);
		System.out.println("infoResponse: " + infoResponse);
		
		// 2.심볼만 추출
		List<Map<String, Object>> symbols = (List<Map<String, Object>>) infoResponse.get("symbols");
		System.out.println("symbols: " + symbols);
		
		// 3.USDT만 필터링
		List<String> usdtPairs = symbols.stream()
				.map(s -> (String)s.get("symbol"))
				.filter(s -> s.endsWith("USDT"))
				.collect(Collectors.toList());
		System.out.println("usdtPairs: " + usdtPairs);
		
		
		
		// 4.각 코인 전날 종가 가져오기
		List<Map<String, Object>> results = new ArrayList<Map<String,Object>>();
		for(String symbol : usdtPairs){
			try {
				String url = "https://api.binance.com/api/v3/klines?symbol=" + symbol + "&interval=1d&limit=2";
				Object[][] data = restTemplate.getForObject(url, Object[][].class);
				if(data == null || data.length < 2) continue;
				// 전일 종가 index 4 
				// limit이 2라는건 어제,오늘것을 가져오는 의미로 data[0]이 어제것.
				Object[] prevClosingInfo = data[data.length - 2];
				double prevClosingPrice = Double.parseDouble((String) prevClosingInfo[4]);
				
				String baseSymbol = symbol.replaceAll("(USDT|BUSD|TUSD|USDC)+$", "").toLowerCase();

				String logoUrl = String.format(
					    "https://assets.coincap.io/assets/icons/%s@2x.png",
					    baseSymbol
					);
				
				LocalDate yesterday = LocalDate.now().minusDays(1);
				PrevClosingPrice dto = new PrevClosingPrice();
				dto.setCloseDate(yesterday);
				dto.setSymbol(symbol);
				dto.setPrice(prevClosingPrice);
				dto.setLogoUrl(logoUrl);
				
				mapper.insertPrevClosingPrice(dto);
			}catch(Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}
}


/*
 [
    1697001600000,         // 0. openTime (캔들 시작 시각, epoch ms)
    "0.5031",              // 1. open (시가)
    "0.5099",              // 2. high (고가)
    "0.4988",              // 3. low (저가)
    "0.5055",              // 4. close (종가 ✅ 우리가 쓰는 값)
    "18523897.100",        // 5. volume (거래량)
    1697087999999,         // 6. closeTime (캔들 종료 시각, epoch ms)
    "9356261.45",          // 7. quoteAssetVolume (거래된 금액, USDT 기준)
    45638,                 // 8. numberOfTrades (거래 횟수)
    "9271429.24",          // 9. takerBuyBaseAssetVolume (매수자 기준 거래량, XRP 수량)
    "4712841.78",          // 10. takerBuyQuoteAssetVolume (매수자 기준 거래금액, USDT)
    "0"                    // 11. ignore (무시해도 됨)
  ],
 */
