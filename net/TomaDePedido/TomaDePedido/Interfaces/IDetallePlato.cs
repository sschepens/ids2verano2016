namespace TomaDePedido.Interfaces
{
    using TomaDePedido.Enums;

    public interface IDetallePlato
    {
        int CodigoDetallePlato { get; }
        int CodigoPlato { get; }
        Enums.EstadoPedido Estado { get; }
        string Comentario { get; set; }

        void AsignarComentario(string comentario);

        string ObtenerComentario();

        int ObtenerCodigoDetalle();

        int ObtenerCodigoPlato();
    }
}