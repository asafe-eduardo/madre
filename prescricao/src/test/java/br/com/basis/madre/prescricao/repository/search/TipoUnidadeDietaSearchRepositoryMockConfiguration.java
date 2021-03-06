package br.com.basis.madre.prescricao.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link TipoUnidadeDietaSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class TipoUnidadeDietaSearchRepositoryMockConfiguration {

    @MockBean
    private TipoUnidadeDietaSearchRepository mockTipoUnidadeDietaSearchRepository;

}
