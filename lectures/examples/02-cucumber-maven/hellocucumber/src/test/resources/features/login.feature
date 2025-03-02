Feature: Teste de Login

  Scenario: Login com credenciais corretas
    Given o usuário está na página de login
    When ele digita usuário "admin" e senha "1234"
    Then ele deve ver a mensagem "Usuário autenticado com sucesso!"

  Scenario: Login com credenciais incorretas
    Given o usuário está na página de login
    When ele digita usuário "admin" e senha "123"
    Then ele deve ver a mensagem "Usuário ou senha incoretos! Tente novamente."
