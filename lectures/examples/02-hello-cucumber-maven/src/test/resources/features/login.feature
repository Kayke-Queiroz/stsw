Feature: Teste de Login

  Scenario: Login com credenciais corretas
    Given usuário está na página de login
    And informa o login "admin" e senha "1234"
    When usuário clica no botão logar
    Then usuário deve receber a mensagem "Usuário autenticado com sucesso!"

  Scenario: Login com credenciais incorretas
    Given usuário está na página de login
    And informa o login "admin" e senha "123"
    When usuário clica no botão logar
    Then usuário deve receber a mensagem "Usuário ou senha incoretos! Tente novamente."
