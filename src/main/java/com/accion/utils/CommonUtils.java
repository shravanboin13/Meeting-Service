package com.accion.utils;
import com.accion.dto.SearchQueryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
  * This class will define common util methods for template service
  * Created by AL1867 on 7/11/2017.
  */
@Component
public class CommonUtils {
    public static final String SORTBYASC="A";
    public static final String SORTBYDESC="D";
    /**
         * This method will return the pagerequest object with the input data receiving from view layer
         * @param pageNumber
         * @param size
         * @return
     * */
   public PageRequest getPageDetails(int pageNumber,int size,String sortBy,String sortOrder){
       PageRequest page = null;
          if(sortOrder.equals(SORTBYASC)) {
              page = new PageRequest(pageNumber-1, size, Sort.Direction.ASC, sortBy);
          }
       if(sortOrder.equals(SORTBYDESC)) {
           page = new PageRequest(pageNumber-1, size, Sort.Direction.DESC, sortBy);
       }

              return page;
      }

    /**
     * This method will construct the data transfer object for search criteria
     * @param queryMap
     * @return
     */
    public SearchQueryDTO createSearchQueryDTO(Map queryMap) {
        return  new SearchQueryDTO(Integer.parseInt((String) queryMap.get("page")),
                Integer.parseInt((String) queryMap.get("size")),
                (String)queryMap.get("sortBy"),
                (String)queryMap.get("sortOrder"));
    }

    /**
     * This method will construct the data transfer object for search criteria
     * @param queryMap
     * @return
     */
    public SearchQueryDTO createSearchQueryDTOByName(Map queryMap) {
        return  new SearchQueryDTO(Integer.parseInt((String) queryMap.get("page")),
                Integer.parseInt((String) queryMap.get("size")),
                (String)queryMap.get("sortBy"),
                (String)queryMap.get("sortOrder"),(String)queryMap.get("name"));
    }
    /**
     * This method will construct page using DTO
     * @param searchQueryDTO
     * @return
     */
    public PageRequest getPageDetails(SearchQueryDTO searchQueryDTO) {
        PageRequest page = null;
        if(searchQueryDTO.getSortOrder().equals(SORTBYASC)) {
            page = new PageRequest(searchQueryDTO.getPage()-1, searchQueryDTO.getSize(), Sort.Direction.ASC, searchQueryDTO.getSortBy());
        }
        if(searchQueryDTO.getSortOrder().equals(SORTBYDESC)) {
            page = new PageRequest(searchQueryDTO.getPage()-1, searchQueryDTO.getSize(), Sort.Direction.DESC, searchQueryDTO.getSortBy());
        }

        return page;
    }
    /**
     * This method will construct the data transfer object for search criteria
     * @param queryMap
     * @return
     */
    public SearchQueryDTO createSearchQueryDTOForCategory(Map queryMap) {
        return  new SearchQueryDTO(Integer.parseInt((String) queryMap.get("page")),
                Integer.parseInt((String) queryMap.get("size")),
                (String)queryMap.get("sortBy"),
                (String)queryMap.get("sortOrder"),
                (String)queryMap.get("categoryType")

        );
    }

    public SearchQueryDTO createSearchQueryDTOQualifiersByName(Map queryMap) {
        return  new SearchQueryDTO(Integer.parseInt((String) queryMap.get("page")),
                Integer.parseInt((String) queryMap.get("size")),
                (String)queryMap.get("sortBy"),
                (String)queryMap.get("sortOrder"),
                (String)queryMap.get("categoryType"),
                (String)queryMap.get("name")

        );
    }
}
