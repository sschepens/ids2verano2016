namespace TomaDePedido.Gestores
{
    using System;
    using TomaDePedido.Interfaces;

    /// <summary>
    /// Gestiona la comunicación con el módulo de Cocina
    /// </summary>
    public class GestorCocina : IGestorCocina
    {
        public void CancelarPlato(int codigoDetalle)
        {
            throw new NotImplementedException();
        }

        public void PedirPlato(IDetallePlato detalle)
        {
            throw new NotImplementedException();
        }
    }
}
