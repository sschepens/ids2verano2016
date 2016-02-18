namespace TomaDePedido.Interfaces
{
    public interface IDetalleCerveza
    {
        int CodigoDetalleCerveza { get; }
        int CodigoCerveza { get; }
        
        int Cantidad { get; set; }

        int ObtenerCodigoDetalle();

        int ObtenerCodigoCerveza();
    }
}