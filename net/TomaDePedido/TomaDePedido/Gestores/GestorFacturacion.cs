namespace TomaDePedido.Gestores
{
    using System;
    using System.Collections.Generic;
    using TomaDePedido.Interfaces;

    /// <summary>
    /// Gestiona la comunicación con el módulo de Facturacíon
    /// </summary>
    public class GestorFacturacion : IGestorFacturacion
    {
        public void AbrirMesa(int codigo)
        {
            throw new NotImplementedException();
        }

        public void CerrarMesa(int codigo)
        {
            throw new NotImplementedException();
        }

        public void EnviarPedido(IPedido pedido)
        {
            throw new NotImplementedException();
        }

        public int ObtenerEstadoMesa(int codigo)
        {
            throw new NotImplementedException();
        }

        public List<IMesa> ObtenerMesas()
        {
            throw new NotImplementedException();
        }

        public List<IPedido> ObtenerPedidos(int codigo)
        {
            throw new NotImplementedException();
        }

        public double ObtenerSaldoMesa(int codigo)
        {
            throw new NotImplementedException();
        }
    }
}
