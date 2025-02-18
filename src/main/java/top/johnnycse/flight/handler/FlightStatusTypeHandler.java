package top.johnnycse.flight.handler;


import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import top.johnnycse.flight.enums.FlightStatus;

import java.sql.*;

public class FlightStatusTypeHandler extends BaseTypeHandler<FlightStatus> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, FlightStatus parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getCode());
    }

    @Override
    public FlightStatus getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int code = rs.getInt(columnName);
        return FlightStatus.fromCode(code);
    }

    @Override
    public FlightStatus getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int code = rs.getInt(columnIndex);
        return FlightStatus.fromCode(code);
    }

    @Override
    public FlightStatus getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int code = cs.getInt(columnIndex);
        return FlightStatus.fromCode(code);
    }
}
