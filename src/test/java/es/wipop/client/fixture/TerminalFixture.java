package es.wipop.client.fixture;

import es.wipop.client.domain.Terminal;

public final class TerminalFixture {

   private TerminalFixture() {
   }

   public static Terminal getTerminal(String terminalId) {
      final var terminal = new Terminal();
      terminal.setId(terminalId);
      return terminal;
   }
}
