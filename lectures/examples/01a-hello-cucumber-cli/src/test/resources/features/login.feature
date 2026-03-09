Feature: Teste de Login

  Scenario: Login com credenciais corretas
    Given o usuário está na página de login
    When ele digita usuário "admin" e senha "123456" e codigo de verificação 1
    Then ele deve ver a mensagem "Login realizado com sucesso"

  Scenario: Login com credenciais incorretas
    Given o usuário está na página de login
    When ele digita usuário "admin" e senha "1234" e codigo de verificação 2
    Then ele deve ver a mensagem "Usuário não autenticado."