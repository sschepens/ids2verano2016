using System.Collections.Generic;

namespace TomaDePedido.Interfaces
{
    using TomaDePedido.Enums;
    using TomaDePedido.Models;

    public interface IMesa
    {
        string Nombre { get; set; }
        int CodigoMesa { get; set; }
        Enums.EstadoMesa Estado { get; set; }
        List<Pedido> Pedidos { get; }
    }
}
