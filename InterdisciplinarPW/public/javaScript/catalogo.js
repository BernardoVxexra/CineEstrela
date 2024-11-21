document.addEventListener('DOMContentLoaded', function () {
  const modal = document.getElementById('confirmModal');
  const confirmYes = document.getElementById('confirmYes');
  const confirmNo = document.getElementById('confirmNo');
  let currentFilmName = null;

  // Abrir a janela de confirmação ao clicar no botão "Excluir"
  document.querySelectorAll('.btn-excluir').forEach(button => {
    button.addEventListener('click', function () {
      currentFilmName = this.getAttribute('data-name');
      modal.style.display = 'flex'; // Mostra a janela
    });
  });

  // Fechar a janela ao clicar em "Não"
  confirmNo.addEventListener('click', function () {
    modal.style.display = 'none'; // Oculta a janela
    currentFilmName = null;
  });

  // Confirmar a exclusão ao clicar em "Sim"
  confirmYes.addEventListener('click', function () {
    if (currentFilmName) {
      // Fazer a requisição para excluir o filme via POST
      fetch('excluirFilme.php', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: `nome=${encodeURIComponent(currentFilmName)}`
      })
      .then(response => response.json())
      .then(data => {
        if (data.success) {
          alert(data.message);
          location.reload(); // Recarrega a página
        } else {
          alert(data.message);
        }
      })
      .catch(error => {
        console.error('Erro:', error);
      })
      .finally(() => {
        modal.style.display = 'none';
      });
    }
  });
});
