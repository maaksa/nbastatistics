package repositories;

import entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Query("select sum(e.value) from Player p join fetch Event e where e.player.id = :id and e.type = entity.enums.Type.POINT")
    int totalPoints(long id);

    @Query("select avg(e.value) from Player p join fetch Event e where e.player.id = :id and e.type = entity.enums.Type.POINT")
    double averagePoints(long id);

    @Query("select avg(e.value) from Player p join fetch Event e where e.player.id = :id and e.type = entity.enums.Type.ASSIST")
    double averageAssists(long id);

    @Query("select avg(e.value) from Player p join fetch Event e where e.player.id = :id and e.type = entity.enums.Type.JUMP")
    double averageRebounds(long id);

}
