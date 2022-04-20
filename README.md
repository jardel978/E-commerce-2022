<h1 align="center">CTD-E-Commerce-2022-T1FT</h1>
<h3>Projeto da disciplina de Backend I do Certified Tech Developer - CTD | Turma 01 Full Time</h3>

<h2>Dependências para o <em>pom.xml</em></h2>

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
  <groupId>com.h2database</groupId>
  <artifactId>h2</artifactId>
  <scope>runtime</scope>
</dependency>
<dependency>
  <groupId>mysql</groupId>
  <artifactId>mysql-connector-java</artifactId>
  <scope>runtime</scope>
</dependency>
<dependency>
  <groupId>org.postgresql</groupId>
  <artifactId>postgresql</artifactId>
  <scope>runtime</scope>
</dependency>
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-security</artifactId>
</dependency>
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-test</artifactId>
  <scope>test</scope>
</dependency>
<dependency>
  <groupId>org.springframework</groupId>
  <artifactId>spring-beans</artifactId>
  <version>5.3.18</version>
</dependency>
```

<h2>Arquivos de configuração do projeto</h2>

<h3>application.properties</h3>

```bash
spring.profiles.active=test
spring.jpa.open-in-view=false
```

<h3>application-test.properties</h3>

```bash
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.url=jdbc:h2:mem:ctdecommerce
spring.datasource.username=sa
spring.datasource.password=

spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

spring.jpa.hibernate.ddl.auto=create-drop
spring.jpa.database-palataform=spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.show-sql=true
spring.jpa.defer-datasource-initialization=true
spring.jpa.properties.hibernate.format_sql=true
```

<h3>application-devmysql.properties</h3>

```bash
#spring.jpa.properties.javax.persistence.schema-generation.create-source=metadata
#spring.jpa.properties.javax.persistence.schema-generation.scripts.action=create
#spring.jpa.properties.javax.persistence.schema-generation.scripts.create-target=create.sql
#spring.jpa.properties.hibernate.hbm2ddl.delimiter=;

spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/ctdecommerce
spring.datasource.username=root
spring.datasource.password=12345678
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
spring.jpa.hibernate.ddl-auto=none
```

<h3>application-devpost.properties</h3>

```bash
#spring.jpa.properties.javax.persistence.schema-generation.create-source=metadata
#spring.jpa.properties.javax.persistence.schema-generation.scripts.action=create
#spring.jpa.properties.javax.persistence.schema-generation.scripts.create-target=createpost.sql
#spring.jpa.properties.hibernate.hbm2ddl.delimiter=;

spring.datasource.url=jdbc:postgresql://localhost:5432/ctdecommerce
spring.datasource.username=postgres
spring.datasource.password=12345678

spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
spring.jpa.hibernate.ddl-auto=none
```

<h3>application-prod.properties</h3>

```bash
spring.datasource.url=${DATABASE_URL}
```

<h3>system.properties | <em>Criar na raiz do projeto</em></h3>

```bash
java.runtime.version=11
```

<h3>Configuração do CORS</h3>

<h4>Security.class</h4>

```bash
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment env;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
            http.headers().frameOptions().disable();
        } // Para funcionar no h2

        http.cors().and().csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().anyRequest().permitAll();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
        configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
```

<h2>Validar o perfil Dev no PostgreSQL</h2>

<h3>Criação das tabelas e seeding no BD local</h3>

<h3>Testar todos os endpoints via Postman</h3>

<h2>Heroku</h2>

<h3>Criação de um aplicativo no site do Heroku</h3>

<ul>
  <li>New -> Create New App (ctdecommerce)</li>
  <li>Provisionar: Resources -> Add ons Postgres -> Heroku Postgres (Free)</li>
  <li>Base remota: Settings -> Reveal Config Vars</li>
  <li>Copiar: Host, Port, Database, Username e Password</li>
</ul>

<h3>Criação do servidor no PgAdmin (local) que aponta para a base dados no Heroku</h3>

<h4>No PgAdmin: Servers, Register Server:</h4>

<ul>
  <li>Heroku-CTD-Ecommerce</li>
  <li>Host: ec2-44-194-92-192.compute-1.amazonaws.com</li>
  <li>Port: 5432</li>
  <li>Database: d6vtv1qbdnsf69</li>
  <li>Username: fqgfxgboszcqqo</li>
  <li>Password: 2bcd82091f98a0d98a81c2f364784be25b3fd051af43d29296a192c470353634</li>
  <li>Advanced: DB restriction: d6vtv1qbdnsf69</li>
</ul>

<h3>Scripts SQL</h3>

<h4>Criação das tabelas e seus relacionamentos</h4>

```sql
create table categories (id int4 generated by default as identity, atualizado TIMESTAMP WITHOUT TIME ZONE, criado TIMESTAMP WITHOUT TIME ZONE, name varchar(255), primary key (id));
create table products (id int4 generated by default as identity, atualizado TIMESTAMP WITHOUT TIME ZONE, criado TIMESTAMP WITHOUT TIME ZONE, description TEXT, image varchar(255), price float8, title varchar(255), primary key (id));
create table productscategories (product_id int4 not null, category_id int4 not null, primary key (product_id, category_id));
alter table if exists productscategories add constraint FKlq6mtk2t102t2n3fsxx0ocvbn foreign key (category_id) references categories;
alter table if exists productscategories add constraint FKs0sm26efqapn2ka3yxgay6p28 foreign key (product_id) references products;
```

<h4>Seeding</h4>

```sql
INSERT INTO categories (name, criado) VALUES ('Computadores', NOW());
INSERT INTO categories (name, criado) VALUES ('Notebooks', NOW());
INSERT INTO categories (name, criado) VALUES ('Tablets e Celulares', NOW());
INSERT INTO categories (name, criado) VALUES ('TV', NOW());
INSERT INTO categories (name, criado) VALUES ('Promoção de Páscoa', NOW());

INSERT INTO products (title, description, price, image, criado) VALUES
('Computador Completo Fácil Intel 4GB SSD 120GB Monitor 19.',
'Nossos computadores são equipados com os mais recentes processadores de alta velocidade, maximizando sua produção.',
1729.50, 'Caminho da imagem', NOW());

INSERT INTO products (title, description, price, image, criado) VALUES
('Intel Core i5 8GB SSD 240GB Monitor 19.',
'Execute suas atividades de forma eficaz e com alto desempenho e baixo consumo de energia.',
2066.10, 'Caminho da imagem', NOW());

INSERT INTO products (title, description, price, image, criado) VALUES
('Computador Pc Cpu Intel Core i3 Com Hdmi 4GB HD 500GB Windows 10 Pro Desktop.', 'Computador Roca Forte projetado para garantir a produtividade no seu dia a dia O desempenho que você precisa para uma jornada eficiente.',
1423.90, 'Caminho da imagem', NOW());

INSERT INTO products (title, description, price, image, criado) VALUES
('Computador Desktop Intel Core i5 Com Bluetooth 8GB HD 500GB Hdmi Windows 10.',
'Este computador foi feito para atender todas as necessidades do seu cotidiano, proporcionando a você robustez, design moderno e rapidez.',
1615.40, 'Caminho da imagem', NOW());

INSERT INTO products (title, description, price, image, criado) VALUES
('Computador Pc Cpu Intel Core i5 Com Hdmi 8GB HD 500GB Windows 10 Teclado Mouse Desktop.',
'Acompanhado dos processadores Intel da linha Core i5, este computador foi feito para atender todas as necessidades do seu cotidiano.',
2250.70, 'Caminho da imagem', NOW());

INSERT INTO products (title, description, price, image, criado) VALUES
('Notebook Dell Inspiron I15-3501-A10P Intel Pentium Gold-7505 4GB 128GB W10 HD 15.6.',
'Processador Intel Pentium Gold: ideal para as tarefas do dia a dia. Memória e armazenamento pensados para o dia a dia.',
2612.50, 'Caminho da imagem', NOW());

INSERT INTO products (title, description, price, image, criado) VALUES
('Notebook Acer Aspire 5 A514-54-52TY Intel Core i5 11ª Gen Windows 11 Home 8GB 256GB sdd 14.',
'Modelo: Notebook Acer Aspire 5 A514-54-52TYSistema Operacional:Windows 11 Homecpu e Chipset:Intel Core i5 – 1135G7 Quad Core.',
1863.55, 'Caminho da imagem', NOW());

INSERT INTO products (title, description, price, image, criado) VALUES
('Tablet Samsung Galaxy A T295 32GB 4G Tela 8 Android Quad-Core 2GHz, 2GB RAM.',
'Descubra um companheiro prático no Galaxy Tab A (8.0”), um tablet que se destaca do básico e acrescenta algo a mais.',
1029.60, 'Caminho da imagem', NOW());

INSERT INTO products (title, description, price, image, criado) VALUES
('Smartphone Motorola Moto E7 Dual Chip Android 10 Tela 6.5.',
'Desenvolvido com tecnologia de ponta, o Smartphone Motorola E7 conta com performance super responsiva, de processador octa-core de 2.0 GHz.',
798.0, 'Caminho da imagem', NOW());

INSERT INTO products (title, description, price, image, criado) VALUES
('Smart TV 50\" UHD Samsung 4k 50AU7700 Processador Crystal 4k Tela.',
'Com uma exclusiva tela sem bordas aparentes, a TV destaca apenas as imagens que você quer ver e nada mais.',
3100.99, 'Caminho da imagem', NOW());

INSERT INTO products (title, description, price, image, criado) VALUES
('Smart Tv 43\" UHD Samsung 4k 43AU7700 Processador Crystal 4k Tela Sem Limites Alexa Built In Controle Único.',
'O processador Crystal 4K transforma tudo o que você assiste em resolução próxima à 4K. Com uma exclusiva tela sem bordas aparentes.',
2410.60, 'Caminho da imagem', NOW());

INSERT INTO products (title, description, price, image, criado) VALUES
('Smart TV LG OLED 55 4K OLED55A1 Dolby Vision IQ Dolby Atmos Inteligência Artificial.',
'Os pixels que se autoiluminam dispensam os painéis de luz de fundo, garantindo telas extremamente finas que podem ser instaladas bem rente à parede com o suporte exclusivo.',
1780.89, 'Caminho da imagem', NOW());

INSERT INTO productscategories (product_id, category_id) VALUES (1, 1);
INSERT INTO productscategories (product_id, category_id) VALUES (2, 1);
INSERT INTO productscategories (product_id, category_id) VALUES (3, 1);
INSERT INTO productscategories (product_id, category_id) VALUES (4, 1);
INSERT INTO productscategories (product_id, category_id) VALUES (5, 1);
INSERT INTO productscategories (product_id, category_id) VALUES (6, 2);
INSERT INTO productscategories (product_id, category_id) VALUES (7, 2);
INSERT INTO productscategories (product_id, category_id) VALUES (8, 3);
INSERT INTO productscategories (product_id, category_id) VALUES (9, 3);
INSERT INTO productscategories (product_id, category_id) VALUES (10, 4);
INSERT INTO productscategories (product_id, category_id) VALUES (11, 4);
INSERT INTO productscategories (product_id, category_id) VALUES (12, 4);
INSERT INTO productscategories (product_id, category_id) VALUES (2, 5);
INSERT INTO productscategories (product_id, category_id) VALUES (5, 5);
INSERT INTO productscategories (product_id, category_id) VALUES (7, 5);
INSERT INTO productscategories (product_id, category_id) VALUES (8, 5);
INSERT INTO productscategories (product_id, category_id) VALUES (10, 5);
```

<h3>Vincular ao GitHub o projeto | Acessar a pasta que está o workspace (Onde está a pasta do projeto)</h3>

```bash
git init
git config --global user.email "mgoncalves@digitalhouse.com"
git config --global user.name "Marcelo Gonçalves de Souza"
git remote add origin https://github.com/prof-marcelosouza/E-commerce-2022.git
git branch -M main
git add .
git commit -m "Projeto CTD E-commerce 2022 concluído."
git push -u origin main
```

<h3>Execução dos comandos do Heroku CLI</h3>

```bash
heroku login
heroku git:remote -a <nome-do-app>
git remote -v
git subtree push --prefix ctdecommerce heroku main
```

<h3>Voltar no site do Heroku e clicar em Open app</h3>

<h4>Copiar a URL e alterar no Postman as requisições</h4>

<h2>Alterando as requisições do Postman</h2>

<h3>Criação de uma variável de ambiente</h3>

<ul>
  <li>New -> Environment</li>
  <li>Add Environment -> Ecommerce</li>
  <li>VARIABLE -> host</li>
  <li>INITIAL VALUE -> http://localhost:8080</li>
  <li>CURRENT VALUE -> http://localhost:8080</li>
  <li>Ativar o Environment recém criado</li>
  <li>Substituir nas requisições:http://localhost:8080 -> {{host}}/<API> </li>
</ul>

<h3>Alteração da variável de ambiente para o Heroku</h3>

<ul>
  <li>INITIAL VALUE -> http://localhost:8080</li>
  <li>CURRENT VALUE -> http://<URL_HEROKU></li>
</ul>

<h2>PARABÉNS! Seu projeto está rodando na nuvem.</h2>
