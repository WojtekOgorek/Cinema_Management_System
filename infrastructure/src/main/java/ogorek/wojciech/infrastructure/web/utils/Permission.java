package ogorek.wojciech.infrastructure.web.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Permission {
    private String uri;
    private Set<String> getMethod;

}
