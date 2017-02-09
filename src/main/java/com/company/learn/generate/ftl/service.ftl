package ${packageName};

<#if !implflag>
import ${modelpackageName}.${className};
import ${daopackageName}.${className}Dao;

public interface ${className}Service {

public void insert(${className} ${className?lower_case});
public void del(${className} ${className?lower_case});

}
<#else>
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import ${modelpackageName}.${className};
import ${daopackageName}.${className}Dao;
import ${ImplpackageName};

@Service("${className?lower_case}Service")
public class ${className}ServiceImpl implements ${className}Service {
@Resource ${className}Dao ${className?lower_case}Dao;
public void insert(${className} ${className?lower_case}){
${className?lower_case}Dao.insert(${className?lower_case});
}

public void del(${className} ${className?lower_case}){
${className?lower_case}Dao.del(${className?lower_case});
}

}
</#if>