Feature: Teste de Login

  Scenario: Login com credenciais corretas
    Given usuário está na página de login
    When usuário digita usuário "admin" e senha "1234"
    Then usuário deve receber a mensagem "Usuário autenticado com sucesso!"

  Scenario: Login com credenciais incorretas
    Given usuário está na página de login
    When usuário digita usuário "admin" e senha "123"
    Then usuário deve receber a mensagem "Usuário ou senha incoretos! Tente novamente."
