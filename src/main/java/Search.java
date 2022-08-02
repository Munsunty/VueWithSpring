import org.springframework.web.bind.annotation.RequestMapping;

public class Search {
    @RequestMapping("/serach")
    public String result(){
        return "hello";
    }
}
