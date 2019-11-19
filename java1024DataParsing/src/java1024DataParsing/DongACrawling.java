package java1024DataParsing;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DongACrawling {

	public static void main(String[] args) {
		// 동아일보 사이트에서 기사 검색을 했을 때 결과 가져오기
		String html = "";
		try {
			String addr = "http://www.donga.com/news/search?query=" + URLEncoder.encode("박문석", "utf8") + "&x=0&y=0";
			URL url = new URL(addr);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			// 연결된 곳의 ContentType
			String headerType = con.getContentType();
			BufferedReader br = null;
			if (headerType.toUpperCase().indexOf("EUC-KR") >= 0) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream(), "EUC-KR"));
			} else {
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			}
			StringBuilder sb = new StringBuilder();
			while (true) {
				String line = br.readLine();
				if (line == null) {
					break;
				}
				sb.append(line + "\n");
			}
			html = sb.toString();
			br.close();
			con.disconnect();
			// System.out.printf("%s\n", html);

		} catch (Exception e) {
			System.out.printf("%s\n", e.getMessage());
		}

		// 기사 건수 만들기
		int cnt = -1;
		try {
			// 파싱준비 - 메모리에 모든 내용 펼치기
			Document document = Jsoup.parse(html);
			// 원하는 부분을 가져오기
			Elements elements = document.select("#contents > div:nth-child(4) > div > h2 > span:nth-child(1)");
			for (int i = 0; i < elements.size(); i = i + 1) {
				Element element = elements.get(i);
				// System.out.printf("%s\n", element.text());
				String str = element.text();
				String[] ar = str.split(" ");
				cnt = Integer.parseInt(ar[1]);
			}
			// System.out.printf("cnt:%d\n", cnt);

		} catch (Exception e) {
			System.out.printf("%s\n", e.getMessage());
		}

		// 동아일보는 한 페이지에 기사가 15개
		// cnt를 가지고 페이지 개수 만들기
		int pagesu = cnt / 15;
		if (cnt % 15 != 0) {
			pagesu = pagesu + 1;
		}
		// 검색된 기사 링크를 저장
		// 기사 링크를 저장할 List
		List<String> articleList = new ArrayList<>();
		try {
			for (int i = 0; i < pagesu; i = i + 1) {
				// 주소 만들기
				String addr = "http://www.donga.com/news/search?p=" + (i * 15 + 1) + "&query="
						+ URLEncoder.encode("박문석", "utf8") + "&check_news=1&more=1&sorting=1"
						+ "&search_date=1&v1=&v2=&range=1";
				URL url = new URL(addr);
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setConnectTimeout(20000);

				String headerType = con.getContentType();
				BufferedReader br = null;
				if (headerType.toUpperCase().indexOf("EUC-KR") >= 0) {
					br = new BufferedReader(new InputStreamReader(con.getInputStream(), "EUC-KR"));
				} else {
					br = new BufferedReader(new InputStreamReader(con.getInputStream()));
				}
				StringBuilder sb = new StringBuilder();
				while (true) {
					String line = br.readLine();
					if (line == null) {
						break;
					}
					sb.append(line + "\n");
				}
				html = sb.toString();
				br.close();
				con.disconnect();
				// System.out.printf("%s\n", html);

				// 파싱 준비
				Document document = Jsoup.parse(html);
				// 파싱
				// 선택자는 앞에서부터 뒤로 갈수록 상세하게 설정된다.
				// 앞쪽부분을 지우게 되면 결과는 더 많이 조회된다.
				Elements element = document.select("div.t > p.tit > a");
				for (int j = 0; j < element.size(); j = j + 1) {
					Element link = element.get(j);
					// 기사 제목을 저장
					// articleList.add(link.text());

					// 링크 저장
					articleList.add(link.attr("href"));
				}
				System.out.printf("%s\n", articleList);

			}
		} catch (Exception e) {
			System.out.printf("기사 링크 가져오기 에러:%s\n", e.getMessage());
		}

		// 리스트에 저장된 링크를 가지고 기사를 찾아서
		// 파일에 기사 내용을 저장하기
		try (PrintWriter pw = new PrintWriter("./article.txt")) {
			// 기사 주소를 순회
			for (String link : articleList) {
				URL url = new URL(link);
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
				StringBuilder sb = new StringBuilder();
				while (true) {
					String line = br.readLine();
					if (line == null) {
						break;
					}
					sb.append(line + "\n");
				}
				html = sb.toString();
				br.close();
				con.disconnect();
				
				Document document = Jsoup.parse(html);
				Elements elements = document.select(
						"#contents > div.article_view > div.article_txt");
				for(int i=0; i<elements.size(); i=i+1) {
					Element element = elements.get(i);
					//태그 안의 내용을 파일에 기록 
					pw.println(element.text());
					pw.flush();
				}
			}

		} catch (Exception e) {
			System.out.printf("기사 가져오기 에러:%s\n", e.getMessage());
		}
	}
}
