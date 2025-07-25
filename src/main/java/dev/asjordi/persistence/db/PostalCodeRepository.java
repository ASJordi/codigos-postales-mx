package dev.asjordi.persistence.db;

import dev.asjordi.model.PostalCode;
import dev.asjordi.persistence.repository.IRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostalCodeRepository implements IRepository<PostalCode> {

    @Override
    public List<PostalCode> getAll() {
        List<PostalCode> list = new ArrayList<>();

        try (var conn = ConnectionDatabase.getInstance();
            var stmt = conn.createStatement();
            var rs = stmt.executeQuery("SELECT * FROM codes")) {

            while (rs.next()) {

                list.add(createPostalCode(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public PostalCode getById(int id) {
        PostalCode cp = null;

        try (var conn = ConnectionDatabase.getInstance();
            var ps = conn.prepareStatement("SELECT * FROM codes WHERE id = ?")) {
            ps.setInt(1, id);

            try (var rs = ps.executeQuery()) {
                if (rs.next()) cp = createPostalCode(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cp;
    }

    @Override
    public void add(PostalCode entity) {
        String sql = "INSERT INTO codes (cpAsentamiento, nombreAsentamiento, tipoAsentamiento, nombreMunicipio, nombreEntidad, nombreCiudad, cpAdministracionPostalAsentamiento, claveEntidad, cpAdministracionPostalAsentamiento2, claveTipoAsentamiento, claveMunicipio, idUnicoAsentamiento, zonaUbicacionAsentamiento, claveCiudad) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try (var conn = ConnectionDatabase.getInstance();
            var ps = conn.prepareStatement(sql)) {

            ps.setString(1, entity.getCpAsentamiento());
            ps.setString(2, entity.getNombreAsentamiento());
            ps.setString(3, entity.getTipoAsentamiento());
            ps.setString(4, entity.getNombreMunicipio());
            ps.setString(5, entity.getNombreEntidad());
            ps.setString(6, entity.getNombreCiudad());
            ps.setString(7, entity.getCpAdministracionPostalAsentamiento());
            ps.setString(8, entity.getClaveEntidad());
            ps.setString(9, entity.getCpAdministracionPostalAsentamiento2());
            ps.setString(10, entity.getClaveTipoAsentamiento());
            ps.setString(11, entity.getClaveMunicipio());
            ps.setString(12, entity.getIdUnicoAsentamiento());
            ps.setString(13, entity.getZonaUbicacionAsentamiento());
            ps.setString(14, entity.getClaveCiudad());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (var conn = ConnectionDatabase.getInstance();
            var ps = conn.prepareStatement("DELETE FROM codes WHERE id = ?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addBatch(List<PostalCode> entities) {
        String sql = "INSERT INTO codes (cpAsentamiento, nombreAsentamiento, tipoAsentamiento, nombreMunicipio, nombreEntidad, nombreCiudad, cpAdministracionPostalAsentamiento, claveEntidad, cpAdministracionPostalAsentamiento2, claveTipoAsentamiento, claveMunicipio, idUnicoAsentamiento, zonaUbicacionAsentamiento, claveCiudad) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try (var conn = ConnectionDatabase.getInstance();
             var ps = conn.prepareStatement(sql)) {

            int batchSize = 1000;
            int count = 0;

            for (PostalCode entity : entities) {
                ps.setString(1, entity.getCpAsentamiento());
                ps.setString(2, entity.getNombreAsentamiento());
                ps.setString(3, entity.getTipoAsentamiento());
                ps.setString(4, entity.getNombreMunicipio());
                ps.setString(5, entity.getNombreEntidad());
                ps.setString(6, entity.getNombreCiudad());
                ps.setString(7, entity.getCpAdministracionPostalAsentamiento());
                ps.setString(8, entity.getClaveEntidad());
                ps.setString(9, entity.getCpAdministracionPostalAsentamiento2());
                ps.setString(10, entity.getClaveTipoAsentamiento());
                ps.setString(11, entity.getClaveMunicipio());
                ps.setString(12, entity.getIdUnicoAsentamiento());
                ps.setString(13, entity.getZonaUbicacionAsentamiento());
                ps.setString(14, entity.getClaveCiudad());
                ps.addBatch();

                if (++count % batchSize == 0) {
                    ps.executeBatch();
                }
            }

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private PostalCode createPostalCode(ResultSet rs) throws SQLException {
        PostalCode cp = new PostalCode();
        cp.setId(rs.getInt("id"));
        cp.setCpAsentamiento(rs.getString("cpAsentamiento"));
        cp.setNombreAsentamiento(rs.getString("nombreAsentamiento"));
        cp.setTipoAsentamiento(rs.getString("tipoAsentamiento"));
        cp.setNombreMunicipio(rs.getString("nombreMunicipio"));
        cp.setNombreEntidad(rs.getString("nombreEntidad"));
        cp.setNombreCiudad(rs.getString("nombreCiudad"));
        cp.setCpAdministracionPostalAsentamiento(rs.getString("cpAdministracionPostalAsentamiento"));
        cp.setClaveEntidad(rs.getString("claveEntidad"));
        cp.setCpAdministracionPostalAsentamiento2(rs.getString("cpAdministracionPostalAsentamiento2"));
        cp.setClaveTipoAsentamiento(rs.getString("claveTipoAsentamiento"));
        cp.setClaveMunicipio(rs.getString("claveMunicipio"));
        cp.setIdUnicoAsentamiento(rs.getString("idUnicoAsentamiento"));
        cp.setZonaUbicacionAsentamiento(rs.getString("zonaUbicacionAsentamiento"));
        cp.setClaveCiudad(rs.getString("claveCiudad"));
        return cp;
    }
}
