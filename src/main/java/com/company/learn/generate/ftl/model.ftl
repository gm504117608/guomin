package ${packageName};

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ${className} {
<#list attrs as a>
private ${a.type} ${a.field}; // ${a.comment}
</#list>

<#list attrs as a>
public void set${a.field?cap_first}(${a.type} ${a.field}){
this.${a.field} = ${a.field};
}

public ${a.type} get${a.field?cap_first}(){
return this.${a.field};
}
</#list>
}