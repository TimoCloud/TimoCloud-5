package cloud.timo.TimoCloud.core.cloudflare;


import cloud.timo.TimoCloud.lib.json.JsonObjectBuilder;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@EqualsAndHashCode
public class DnsRecord {

    @Getter
    private String id;
    @Getter
    private String type;
    @Getter
    private String name;
    @Getter
    private String content;
    @Getter
    private int ttl;
    @Getter
    private DnsZone zone;

    public DnsRecord() {}

    public JsonObject toJson() {
        return JsonObjectBuilder.create()
                .setIfNotNull("id", getId())
                .set("type", getType())
                .set("name", getName())
                .set("content", getContent())
                .set("ttl", getTtl())
                .set("zone_id", getZone().getId())
                .set("zone_name", getZone().getName())
                .toJsonObject();
    }

    public static DnsRecord fromJson(JsonObject jsonObject) {
        return new DnsRecord(
                jsonObject.get("id").getAsString(),
                jsonObject.get("type").getAsString(),
                jsonObject.get("name").getAsString(),
                jsonObject.get("content").getAsString(),
                jsonObject.get("ttl").getAsInt(),
                new DnsZone(
                        jsonObject.get("zone_id").getAsString(),
                        jsonObject.get("zone_name").getAsString()
                )
        );
    }
}
