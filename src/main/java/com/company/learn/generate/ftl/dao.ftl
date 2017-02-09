package ${packageName};

<#if !implflag>
import ${modelpackageName}.${className};

public interface ${className}Dao {

public void insert(${className} ${className?lower_case});
public void del(${className} ${className?lower_case});

}
<#else>
import javax.annotation.Resource;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import ${modelpackageName}.${className};
import ${ImplpackageName};

@Repository("${className?lower_case}Dao")
public class ${className}DaoImpl implements ${className}Dao {
@Resource HibernateTemplate hibernateTemplate;

public void insert(${className} ${className?lower_case}){
hibernateTemplate.save(${className?lower_case});
}

public void del(${className} ${className?lower_case}){
hibernateTemplate.delete(${className?lower_case});
}

}
</#if>