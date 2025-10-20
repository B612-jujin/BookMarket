package kr.ac.kopo.cjj.bookmarket.serveice;


import kr.ac.kopo.cjj.bookmarket.domain.Order;
import kr.ac.kopo.cjj.bookmarket.repository.OrderProRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class OrderProService {

    @Autowired
    private OrderProRepository orderProRepository;

    public void save(Order order){
        orderProRepository.save(order);
    }


    public Page<Order> listAll(int pageNum, String sortField, String sortDir) {
        int pageSize = 5;
        Sort sort = sortDir.equals("asc")? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNum-1, pageSize, sort );
        return orderProRepository.findAll(pageable);
    }

    public Order get(Long id) {
     return orderProRepository.findById(id).get();
    }

    public void delete(Long id) {
        orderProRepository.deleteById(id);
    }

    public void deleteAll() {
        orderProRepository.deleteAll();
    }

}
